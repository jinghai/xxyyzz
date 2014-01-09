/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.exceptions;

/**
 *
 * @author yneos
 */
public class SizeLimitException extends APIException {

    public SizeLimitException() {
        super();
    }

    public SizeLimitException(String message) {
        super(message);
    }
}
