package com.ipet.android.sdk.core;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apaches.commons.codec.binary.Base64;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import android.util.Log;

/**
 * Api请求拦截
 *
 * @author xiaojinghai
 */
class ApiInterceptor implements ClientHttpRequestInterceptor {

    private final String TAG = "ApiInterceptor";

    private final Charset charset = Charset.forName("UTF-8");

    /**
     * 在每个情求中加入Basic验证头信息
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        request.getHeaders().set("Authorization",
                "Basic " + new String(new Base64().encode((Constant.APP_KEY + ":" + Constant.APP_SECRET).getBytes(charset)), charset));
        request.getHeaders().setAcceptEncoding(ContentCodingType.GZIP);

        if (request.getMethod().equals(HttpMethod.GET)) {
            //request.getHeaders().setIfNoneMatch("-1");
            String url = request.getURI().toString();

            Log.i(TAG, "-->："+url);
            Log.i(TAG, "Etag头：" + request.getHeaders().getIfNoneMatch());
        }

        ClientHttpResponse resp = execution.execute(request, body);

        if (request.getMethod().equals(HttpMethod.GET)) {
            resp.getHeaders().setIfNoneMatch("-1");
            Log.i(TAG, "<--:"+request.getURI().toString());
            Log.i(TAG, "Etag头：" + resp.getHeaders().getETag());
            Log.i(TAG, "状态：" + resp.getRawStatusCode());
        }

        //Log.i(TAG, "-->" + request.getURI() + ":" + resp.getRawStatusCode());
        return resp;
    }

}
