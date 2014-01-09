/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.base;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class ApiBase {

    public RestTemplate restTemplate;

    public ApiBase() {
        restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new ApiExceptionHandler());

        restTemplate.getInterceptors().add(new ApiInterceptor("admin", "admin"));
    }

}
