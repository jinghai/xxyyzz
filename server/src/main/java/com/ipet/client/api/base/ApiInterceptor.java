/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.base;

import java.io.IOException;
import java.nio.charset.Charset;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    private final String username;

    private final String password;

    private final Charset charset;

    public ApiInterceptor(String username, String password) {
        this(username, password, Charset.forName("UTF-8"));
    }

    public ApiInterceptor(String username, String password, Charset charset) {
        this.username = username;
        this.password = password;
        this.charset = charset;
    }

    /**
     * 在每个情求中加入Basic验证头信息
     *
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("Authorization", "Basic " + new String(Base64.encode((username + ":" + password).getBytes(charset)), charset));
        logger.debug(request.getHeaders().toString());
        return execution.execute(request, body);
    }

}
