/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.connect;

import com.ipet.android.sdk.api.Ipet;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Ipet ConnectionFactory implementation.
 *
 * @author xiaojinghai
 */
public class IpetConnectionFactory extends OAuth2ConnectionFactory<Ipet> {

    /**
     * Creates a factory for Ipet connections.
     *
     * @param clientId client ID
     * @param clientSecret client secret
     */
    public IpetConnectionFactory(String clientId, String clientSecret) {
        super("ipet", new IpetServiceProvider(clientId, clientSecret), new IpetAdapter());
    }

}
