/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.base;

import org.springframework.web.client.RestTemplate;

/**
 * API基类, 提供统一RestTemplate对象
 *
 * @author xiaojinghai
 */
public class ApiBase {

    private RestTemplate restTemplate;

    private final String fileHost = "http://localhost:8084/server/";

    private final String apiHost = "http://localhost:8084/server/api/v1/";

    public ApiBase(String appKey, String appSecret) {
        restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new ApiExceptionHandler());

        restTemplate.getInterceptors().add(new ApiInterceptor(appKey, appSecret));
    }

    /**
     * @return the restTemplate
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    /**
     * @return the fileHost
     */
    public String getFileHost() {
        return fileHost;
    }

    /**
     * @return the apiHost
     */
    public String getApiHost() {
        return apiHost;
    }

}
