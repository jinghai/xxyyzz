package com.ipet.server.web.rest.v1;


import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private final ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String theString = IOUtils.toString(response.getBody(), "UTF-8");
        throw new RuntimeException("Error:" + theString);
    }
}
