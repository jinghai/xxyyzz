/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.connect;

import com.ipet.android.sdk.api.Ipet;
import com.ipet.android.sdk.api.impl.IpetTemplate;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Ipet ServiceProvider implementation.
 *
 * @author xiaojinghai
 */
public class IpetServiceProvider extends AbstractOAuth2ServiceProvider<Ipet> {

    public IpetServiceProvider(String clientId, String clientSecret) {
        super(createOAuth2Template(clientId, clientSecret));
    }

    private static OAuth2Template createOAuth2Template(String clientId, String clientSecret) {
        OAuth2Template oAuth2Template = new IpetOAuth2Template(clientId, clientSecret);
        //false for basic auth,see OAuth2Template impl
        oAuth2Template.setUseParametersForClientAuthentication(false);
        return oAuth2Template;
    }

    public Ipet getApi(String accessToken) {
        return new IpetTemplate(accessToken);
    }

}
