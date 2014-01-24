/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.base;

import com.ipet.client.api.domain.IpetUser;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 应用容器，单例，线程安全，存放API范围内公共变量
 *
 * @author xiaojinghai
 */
public class ApiContext {

    private final RestTemplate restTemplate;

    private Boolean isAuthorized;

    private String currUserId;

    //文件服务器地址
    public static final String FILE_SERVER_BASE = "http://localhost:8080/server/";

    //API服务器地址
    public static final String API_SERVER_BASE = "http://localhost:8080/server/api/v1/";

    private static ApiContext instance;

    private ApiContext(String appKey, String appSecret) {
        isAuthorized = false;
        Charset charset = Charset.forName("UTF-8");

        restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(charset));
        //messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new MappingJacksonHttpMessageConverter());

        restTemplate.setMessageConverters(messageConverters);

        restTemplate.setErrorHandler(new ApiExceptionHandler());

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new ApiInterceptor(appKey, appSecret));
        restTemplate.setInterceptors(interceptors);
        if (restTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
            ((SimpleClientHttpRequestFactory) restTemplate
                    .getRequestFactory()).setConnectTimeout(10 * 1000);
            ((SimpleClientHttpRequestFactory) restTemplate
                    .getRequestFactory()).setReadTimeout(10 * 1000);
        } else if (restTemplate.getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {

            ((HttpComponentsClientHttpRequestFactory) restTemplate
                    .getRequestFactory()).setReadTimeout(10 * 1000);
            ((HttpComponentsClientHttpRequestFactory) restTemplate
                    .getRequestFactory()).setConnectTimeout(10 * 1000);
        }
    }

    public static synchronized ApiContext getInstace(String appKey, String appSecret) {
        if (instance == null) {
            instance = new ApiContext(appKey, appSecret);
        }
        return instance;
    }

    public synchronized RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public synchronized Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public synchronized void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public synchronized String getCurrUserId() {
        return currUserId;
    }

    public synchronized void setCurrUserId(String currUser) {
        this.currUserId = currUser;
    }

}
