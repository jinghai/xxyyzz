package com.ipet.android.sdk.cache.http;

import android.util.Log;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 *
 * @author xiaojinghai
 */
public class ETagCachingRestTemplate extends RestTemplate {

    private final String TAG = "ETagCachingRestTemplate";
    private static final String ETAG_HEADER = "ETag";
    private static final String IF_NONE_MATCH_HEADER = "If-None-Match";

    private final MemoryLRUCache cache = new MemoryLRUCache();

    /**
     *
     * @param <T>
     * @param url
     * @param method
     * @param requestCallback
     * @param responseExtractor
     * @return
     * @throws RestClientException
     */
    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor)
            throws RestClientException {

        //如果是不可以被缓存的请求，则透出给父类正常请求
        if (!isCacheableRequest(method)) {
            return super.doExecute(url, method, requestCallback,
                    responseExtractor);
        }
        //如果是get请求，则使用自定义的代理对像，以处理缓存
        return super.doExecute(url, method, new DelegatingRequestCallback(url,
                requestCallback), new DelegatingResponseExtractor<T>(url,
                        method, responseExtractor));
    }

    private boolean isCacheableRequest(HttpMethod method) {

        return GET.equals(method);
    }

    /**
     * 支持缓存的回调 请求前对Request对象进行处理
     */
    private class DelegatingRequestCallback implements RequestCallback {

        private final URI uri;
        private final RequestCallback callback;

        public DelegatingRequestCallback(URI uri, RequestCallback callback) {

            Assert.notNull(uri);

            this.uri = uri;
            this.callback = callback;
        }

        public void doWithRequest(ClientHttpRequest request) throws IOException {
            //如果之前有缓存则增加IF_NONE_MATCH_HEADER头
            synchronized (cache) {
                if (cache.hasEntry(uri.toString())) {
                    request.getHeaders().add(IF_NONE_MATCH_HEADER,
                            cache.get(uri.toString()).getEtag());
                }
            }

            if (null != callback) {
                callback.doWithRequest(request);
            }
        }
    }

    private class DelegatingResponseExtractor<T> implements
            ResponseExtractor<T> {

        private final URI uri;
        private final HttpMethod method;
        private final ResponseExtractor<T> extractor;

        public DelegatingResponseExtractor(URI uri, HttpMethod method,
                ResponseExtractor<T> extractor) {

            Assert.notNull(uri);

            this.uri = uri;
            this.method = method;
            this.extractor = extractor;
        }

        /**
         * Returns a cached instance if the response returns 304 and we already
         * have a cached instance available. Puts the extracted resource into
         * the cache on 200.
         */
        @SuppressWarnings("unchecked")
        public T extractData(ClientHttpResponse response) throws IOException {

            HttpHeaders headers = response.getHeaders();

            boolean isNotModified
                    = NOT_MODIFIED.equals(response.getStatusCode());

            synchronized (cache) {
                // Return cached instance on 304
                if (isNotModified && cache.hasEntry(uri.toString())) {
                    return (T) cache.get(uri.toString());
                }
            }

            T result = extractor.extractData(response);

            // Put into cache if ETag returned
            if (isCacheableRequest(method) && headers.containsKey(ETAG_HEADER)) {
                ObjectMapper mapper = new ObjectMapper();
                Writer strWriter = new StringWriter();
                try {
                    mapper.writeValue(strWriter, result);
                } catch (IOException ex) {

                }
                String jsonStr = strWriter.toString();
                Log.i(TAG, "put:" + jsonStr);
                synchronized (cache) {
                    //TODO:
                    cache.put(new EtagCacheEntry(uri.toString(), jsonStr, headers.getFirst(ETAG_HEADER),
                            20000l));
                }
            }

            return result;
        }
    }

}
