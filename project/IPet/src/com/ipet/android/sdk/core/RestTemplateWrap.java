/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.core;

import android.content.Context;

import com.ipet.android.sdk.cache.RestTemplate4Cache;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Administrator
 */
public class RestTemplateWrap extends RestTemplate {

    private static final String TAG = "RestTemplateWrap";

    private final RestTemplate restTemplate;

    // GET
    public <T> T getForObject(URI url, Class<T> responseType) {

        return restTemplate.getForObject(url, responseType);
    }

    // POST
    public <T> T postForObject(URI url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }

    public RestTemplateWrap(Context context) {
        Charset charset = Charset.forName("UTF-8");

        restTemplate = new RestTemplate4Cache(context);
        //避免HttpURLConnection的http.keepAlive Bug
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10 * 1000);
        factory.setReadTimeout(30 * 1000);
        restTemplate.setRequestFactory(factory);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(charset));
        // messageConverters.add(new MappingJackson2HttpMessageConverter());

        MappingJacksonHttpMessageConverter mjm = new MappingJacksonHttpMessageConverter();
        mjm.getObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        messageConverters.add(mjm);

        restTemplate.setMessageConverters(messageConverters);

        restTemplate.setErrorHandler(new ApiExceptionHandler());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new ApiInterceptor());
        restTemplate.setInterceptors(interceptors);
    }

}
