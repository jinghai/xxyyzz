package com.ipet.android.sdk.base;

import android.content.Context;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * API基类, 提供统一RestTemplate对象和一些常用方法
 *
 * @author xiaojinghai
 */
public class ApiBase {

    private RestTemplate restTemplate;

    private final Context context;

    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public ApiBase(Context ctx) {
        this.context = ctx;
    }

    protected URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    protected URI buildUri(String path, String parameterName, String parameterValue) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(parameterName, parameterValue);
        return buildUri(path, parameters);
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(Constant.API_SERVER_BASE + path).queryParams(parameters).build();
    }

    protected void requireAuthorization() {
        if (!getIsAuthorized()) {
            throw new APIException("用户未登录");
        }
    }

    protected boolean getIsAuthorized() {
        return StateManager.getAuthorized(context);
    }

    protected void setIsAuthorized(boolean bl) {
        StateManager.setAuthorized(context, bl);
    }

    public String getCurrUserId() {
        return StateManager.getUid(context);
    }

    protected void setCurrUserId(String uid) {
        StateManager.setUid(context, uid);
    }

    protected RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }
        return restTemplate;
    }

    //创建RestTemplate对象
    private RestTemplate createRestTemplate() {
        Charset charset = Charset.forName("UTF-8");

        RestTemplate restTemplate = new RestTemplate();
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

        return restTemplate;
    }

}
