package com.ipet.android.sdk.cache.http;

import android.content.Context;
import android.util.Log;
import com.ipet.android.sdk.base.JSONUtil;
import java.io.IOException;
import java.net.URI;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.http.HttpStatus;
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

    // private final MemoryLRUCache memCache = new MemoryLRUCache();
    private final DBLRUCache memCache;

    public ETagCachingRestTemplate(Context context) {
        super();
        memCache = new DBLRUCache(context);
    }

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

            if (memCache.hasEntry(uri.toString())) {
                EtagCacheEntry e = memCache.get(uri.toString());
                Log.i(TAG, "doWithRequest-->增加Etag头:" + e.getEtag());
                request.getHeaders().add(IF_NONE_MATCH_HEADER, e.getEtag());
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
         * have a memCached instance available. Puts the extracted resource into
         * the memCache on 200.
         */
        @SuppressWarnings("unchecked")
        public T extractData(ClientHttpResponse response) throws IOException {

            HttpHeaders headers = response.getHeaders();

            boolean isNotModified = NOT_MODIFIED.equals(response.getStatusCode());
            TypeReference type = new TypeReference<T>() {
            };
            // 如果返回304状态，则直接从缓存取值
            if (isNotModified && memCache.hasEntry(uri.toString())) {
                EtagCacheEntry e = memCache.get(uri.toString());
                Log.i(TAG, "extractData-->304,从缓存取:" + e.getValue());
                return (T) JSONUtil.fromJSON(e.getValue(), type);
            }

            T result = extractor.extractData(response);

            // Put into memCache if ETag returned
            if (isCacheableRequest(method)
                    && headers.containsKey(ETAG_HEADER)
                    && HttpStatus.OK.equals(response.getStatusCode())) {

                String str = JSONUtil.toJson(result);
                Log.i(TAG, "extractData-->200,放入缓存:" + str);
                memCache.put(new EtagCacheEntry(uri.toString(), str, headers.getFirst(ETAG_HEADER), 0l));

            }

            return result;
        }
    }

}
