/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.impl;

import com.ipet.android.sdk.api.ContactOperations;
import com.ipet.android.sdk.api.domain.IpetUser;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class ContactTemplate extends AbstractIpetOperations implements ContactOperations {

    private final RestTemplate restTemplate;

    /**
     * @param restTemplate
     * @param isAuthorizedForUser
     */
    public ContactTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    public void subscribe(long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void unSubscribe(long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IpetUser> getMySubscibes(int index, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IpetUser> getMyfollowers(int index, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IpetUser> getMyfriends(int index, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
