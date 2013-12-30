/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.connect;

import com.ipet.android.sdk.api.Ipet;
import com.ipet.android.sdk.api.domain.IpetUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author xiaojinghai
 */
public class IpetAdapter implements ApiAdapter<Ipet> {

    public boolean test(Ipet ipet) {
        try {
            ipet.userOperations().getUserInfo();
            return true;
        } catch (HttpClientErrorException e) {
            // TODO : Beef up an error handling and trigger off of a more specific exception
            return false;
        }
    }

    public void setConnectionValues(Ipet ipet, ConnectionValues values) {
        IpetUser user = ipet.userOperations().getUserInfo();
        values.setProviderUserId(String.valueOf(user.getId()));
        values.setDisplayName(user.getDisplayName());
        values.setProfileUrl(user.getProfileUrl());
        values.setImageUrl(user.getPictureUrl());
    }

    public UserProfile fetchUserProfile(Ipet ipet) {
        IpetUser user = ipet.userOperations().getUserInfo();
        return new UserProfileBuilder().setName(user.getDisplayName()).setEmail(user.getEmail()).setUsername(user.getName()).build();
    }

    public void updateStatus(Ipet ipet, String message) {
        // not supported
    }

}
