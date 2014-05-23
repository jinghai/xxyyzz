package com.ipet.android.sdk.base;

import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

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
            String rheads = request.getHeaders().toSingleValueMap().toString();
            String url = request.getURI().toString();
            String param = request.getURI().getQuery();
            String content = new String(body, charset);
            Log.i(TAG, "--->发送：");
            Log.i(TAG, "头：" + rheads);
            Log.i(TAG, "地址：" + url);
            Log.i(TAG, "参数：" + param);
            Log.i(TAG, "内容：" + content);
        }

        ClientHttpResponse resp = execution.execute(request, body);

        if (request.getMethod().equals(HttpMethod.GET)) {
            resp.getHeaders().setIfNoneMatch("-1");
            String rheads = resp.getHeaders().toSingleValueMap().toString();
            Log.i(TAG, "接受<---");
            Log.i(TAG, "头：" + rheads);
            Log.i(TAG, "长度：" + resp.getHeaders().getContentLength());
        }

        //Log.i(TAG, "-->" + request.getURI() + ":" + resp.getRawStatusCode());
        return resp;
    }

}
