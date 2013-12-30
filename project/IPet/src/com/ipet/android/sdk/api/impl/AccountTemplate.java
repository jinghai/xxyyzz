/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.impl;

import com.ipet.android.sdk.api.AccountOperations;
import com.ipet.android.sdk.api.domain.IpetUser;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class AccountTemplate extends AbstractIpetOperations implements AccountOperations {

    private final RestTemplate restTemplate;

    /**
     * @param restTemplate
     * @param isAuthorizedForUser
     */
    public AccountTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    public String login(String account, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void register(IpetUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
