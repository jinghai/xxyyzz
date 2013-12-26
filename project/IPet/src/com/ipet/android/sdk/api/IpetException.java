/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import org.springframework.social.SocialException;

/**
 *
 * @author xiaojinghai
 */
public class IpetException extends SocialException {

    public IpetException(String message, Throwable cause) {
        super(message, cause);
    }

}
