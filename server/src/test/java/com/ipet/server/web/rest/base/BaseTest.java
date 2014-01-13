/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.web.rest.base;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class BaseTest {

    public RestTemplate restTemplate;

    public BaseTest() {
        restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new MyErrorHandler());

        restTemplate.getInterceptors().add(new MyHttpRequestInterceptor("admin", "admin"));
    }

}
