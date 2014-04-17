package com.ipet.demo.baidu.placeapi;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Api请求拦截
 *
 * @author xiaojinghai
 */
public class ApiInterceptor implements ClientHttpRequestInterceptor {

    /**
     * 在每个情求中加入Basic验证头信息
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        List<MediaType> accepts = new ArrayList<MediaType>();
        accepts.add(MediaType.APPLICATION_JSON);
        request.getHeaders().setAccept(accepts);
        //System.out.println("----->" + request.getHeaders().toSingleValueMap().toString());
        ClientHttpResponse response = execution.execute(request, body);
        //response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //System.out.println("<-----" + response.getHeaders().toSingleValueMap().toString());
        return response;
    }

}
