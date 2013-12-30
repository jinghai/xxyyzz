/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.impl;

import com.ipet.android.sdk.api.DiscoverOperations;
import com.ipet.android.sdk.api.domain.IpetPhoto;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class DiscoverTemplate extends AbstractIpetOperations implements DiscoverOperations {

    private final RestTemplate restTemplate;

    /**
     * @param restTemplate
     * @param isAuthorizedForUser
     */
    public DiscoverTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    public List<IpetPhoto> getNewer(int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IpetPhoto> getNext(long pid, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
