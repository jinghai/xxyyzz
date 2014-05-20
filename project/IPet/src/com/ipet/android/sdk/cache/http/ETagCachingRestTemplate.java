package com.ipet.android.sdk.cache.http;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private static final String ETAG_HEADER = "ETag";
    private static final String IF_NONE_MATCH_HEADER = "If-None-Match";

    private EtagCache cache = new EtagCache();

    @Override
    protected <T> T doExecute(URI url, HttpMethod method,
            RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor) throws RestClientException {

        if (isPurgingRequest(method)) {

            synchronized (cache) {
                cache.remove(url);
            }

            return super.doExecute(url, method, requestCallback,
                    responseExtractor);
        }

        return super.doExecute(url, method, new DelegatingRequestCallback(url,
                requestCallback), new DelegatingResponseExtractor<T>(url,
                        method, responseExtractor));
    }

    private boolean isCacheableRequest(HttpMethod method) {

        return GET.equals(method);
    }

    private boolean isPurgingRequest(HttpMethod method) {

        return Arrays.asList(POST, PUT, DELETE).contains(method);
    }

    /**
     * Applies an {@value ETagCachingRestTemplate#IF_NONE_MATCH_HEADER} header
     * to the request if there we already have an instance of the requested
     * resource in the cache.
     *
     * @author Oliver Gierke
     */
    private class DelegatingRequestCallback implements RequestCallback {

        private URI uri;
        private RequestCallback callback;

        public DelegatingRequestCallback(URI uri, RequestCallback callback) {

            Assert.notNull(uri);

            this.uri = uri;
            this.callback = callback;
        }

        public void doWithRequest(ClientHttpRequest request) throws IOException {

            // Do we have a cached object? Apply header...
            synchronized (cache) {
                if (cache.hasEntry(uri)) {
                    request.getHeaders().add(IF_NONE_MATCH_HEADER,
                            cache.getEtag(uri));
                }
            }

            if (null != callback) {
                callback.doWithRequest(request);
            }
        }
    }

    private class DelegatingResponseExtractor<T> implements
            ResponseExtractor<T> {

        private URI uri;
        private HttpMethod method;
        private ResponseExtractor<T> extractor;

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
                if (isNotModified && cache.hasEntry(uri)) {
                    return (T) cache.get(uri);
                }
            }

            T result = extractor.extractData(response);

            // Put into cache if ETag returned
            if (isCacheableRequest(method) && headers.containsKey(ETAG_HEADER)) {

                synchronized (cache) {
                    cache.put(new EtagCacheEntry(headers.getFirst(ETAG_HEADER),
                            uri, result));
                }
            }

            return result;
        }
    }

}
