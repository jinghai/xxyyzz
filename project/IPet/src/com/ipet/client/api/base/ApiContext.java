/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.base;

import com.ipet.client.api.domain.IpetUser;
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

    public static final String API_URL_BASE = "http://localhost:8084/server/api/v1/";

    private static ApiContext instance;

    private ApiContext(String appKey, String appSecret) {
        isAuthorized = false;

        restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new ApiExceptionHandler());

        restTemplate.getInterceptors().add(new ApiInterceptor(appKey, appSecret));
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
