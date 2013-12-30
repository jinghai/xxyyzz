/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.connect;

import com.ipet.android.sdk.api.config.IpetApiConfig;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;

import org.springframework.util.MultiValueMap;

public class IpetOAuth2Template extends OAuth2Template {

    //private String clientId;
    // private String clientSecret;
    public IpetOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, IpetApiConfig.OAUTH2_AUTHORIZE_URL, IpetApiConfig.OAUTH2_ACCESS_TOKEN_URL);
        //this.clientId = clientId;
        //this.clientSecret = clientSecret;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {

        return super.postForAccessGrant(accessTokenUrl, parameters);
        //return new AccessGrant(response.getFirst("access_token"), null, null, expires != null ? Long.valueOf(expires) : null);
    }

    /**
     * //自定义http拦截,加入基本验证头信息,受IpetServiceProvider:oAuth2Template.setUseParametersForClientAuthentication(false)控制
     *
     * @Override protected RestTemplate createRestTemplate() {
     *
     * ClientHttpRequestFactory requestFactory =
     * ClientHttpRequestFactorySelector.getRequestFactory(); RestTemplate
     * restTemplate = new RestTemplate(requestFactory);
     * List<HttpMessageConverter<?>> converters = new
     * ArrayList<HttpMessageConverter<?>>(2); converters.add(new
     * FormHttpMessageConverter()); converters.add(new
     * MappingJackson2HttpMessageConverter());
     * restTemplate.setMessageConverters(converters); //basic auth,see
     * OAuth2Template impl
     *
     * restTemplate.getInterceptors().add( new
     * PreemptiveBasicAuthClientHttpRequestInterceptor(clientId, clientSecret));
     *
     * return restTemplate; }
     *
     */
}
