package com.ipet.android.sdk.cache;

import android.content.Context;
import android.util.Log;
import com.ipet.android.sdk.core.APIException;
import com.ipet.android.sdk.util.JSONUtil;
import com.ipet.android.sdk.util.NetWorkUtils;
import java.io.IOException;
import java.net.URI;
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
public class RestTemplate4Cache extends RestTemplate {

    private final String TAG = "ETagCachingRestTemplate";
    private static final String ETAG_HEADER = "ETag";

    // private final MemoryLRUCache cache = new MemoryLRUCache();
    private final Cache cache;

    private final Context context;

    public RestTemplate4Cache(Context ctx, int maxNumL1, int maxNumL2) {
        super();
        context = ctx;
        cache = new Cache(ctx, maxNumL1, maxNumL2);
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor)
            throws RestClientException {
        //如果网络不可用，直接从缓存获取数据
        if (!NetWorkUtils.isNetworkConnected(context)) {

            if (!isCacheableRequest(method)) {
                Log.i(TAG, "doExecute:" + "非get方法网络不可用");
                throw new APIException("网络不可用");
            }

            CacheEntry e = cache.get(url.toString());

            if (null != e) {
                Log.i(TAG, "doExecute:" + "找到离线缓存:" + e.getValue());
                T t = JSONUtil.fromJSON(e.getValue(), e.getClassType());
                return t;
            } else {
                Log.i(TAG, "doExecute:" + "没找到离线缓存");
                throw new APIException("网络不可用");
            }

        }
        //非get请求，则透传给父类正常请求
        if (!isCacheableRequest(method)) {
            Log.i(TAG, "doExecute:" + "不可缓存方法");
            return super.doExecute(url, method, requestCallback,
                    responseExtractor);
        }

        //如果没有到过期时间，则直接返回结果，不产生请求
        CacheEntry e = cache.get(url.toString());

        if (null != e && System.currentTimeMillis() > e.getExpireOn()) {
            Log.i(TAG, "doExecute:" + "找到未过期缓存");
            T t = JSONUtil.fromJSON(e.getValue(), e.getClassType());
            Log.i(TAG, "doExecute:" + "未过期缓存JSON:" + e.getValue());
            Log.i(TAG, "doExecute:" + "转换后:" + t.getClass());
            return t;
        }

        //如果是get请求，则使用自定义的代理对像，以处理缓存
        Log.i(TAG, "doExecute:" + "缓存为空或已过期");
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
            CacheEntry e = cache.get(uri.toString());
            if (null != e) {
                Log.i(TAG, "doWithRequest-->增加Etag头:" + e.getEtag());
                request.getHeaders().setIfNoneMatch(e.getEtag());
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

        public T extractData(ClientHttpResponse response) throws IOException {

            HttpHeaders headers = response.getHeaders();

            boolean isNotModified = NOT_MODIFIED.equals(response.getStatusCode());
            // 如果返回304状态，则直接从缓存取值
            if (isNotModified) {
                CacheEntry e = cache.get(uri.toString());
                Log.i(TAG, "extractData-->304,从缓存取:" + e.getValue());
                T t = JSONUtil.fromJSON(e.getValue(), e.getClassType());
                return t;
            }

            T result = extractor.extractData(response);

            //如果返回200状态并且带有etag头
            if (isCacheableRequest(method)
                    && headers.containsKey(ETAG_HEADER)
                    && HttpStatus.OK.equals(response.getStatusCode())) {
                //处理Etag
                String eTag = response.getHeaders().getETag();
                eTag = eTag == null ? "-1" : eTag;

                //处理CacheControl
                String cacheControl = response.getHeaders().getCacheControl();
                Long expireOn = System.currentTimeMillis();
                if (null != cacheControl && !"".equals(cacheControl)) {
                    String[] t = cacheControl.split("=");
                    String s = null == t[1] ? "0" : t[1];
                    expireOn = System.currentTimeMillis() + (Long.valueOf(s) * 1000);
                }

                String str = JSONUtil.toJson(result);
                String classType = result.getClass().getName();
                Log.i(TAG, "extractData-->200,Result类型:" + classType);
                Log.i(TAG, "extractData-->200,放入缓存:" + str);
                cache.put(new CacheEntry(uri.toString(), str, eTag, expireOn, classType));

            }

            return result;
        }
    }

}
