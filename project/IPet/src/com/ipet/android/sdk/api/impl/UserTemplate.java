/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.impl;

import com.ipet.android.sdk.api.UserOperations;
import com.ipet.android.sdk.api.domain.IpetUser;
import com.ipet.android.sdk.api.domain.IpetUserProfile;
import com.ipet.android.sdk.api.domain.IpetUserSettings;
import java.io.File;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xiaojinghai
 */
public class UserTemplate extends AbstractIpetOperations implements UserOperations {

    private final RestTemplate restTemplate;

    /**
     * @param restTemplate
     * @param isAuthorizedForUser
     */
    public UserTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    public void updateUserInfo(IpetUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateUserPicture(File Picture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateUserSettings(IpetUserSettings settings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void changePassword(String oldP, String newP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public IpetUser getUserInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public IpetUser getOtherUserInfo(long userId) {
        requireAuthorization();
        return restTemplate.getForObject(
                buildUri("users/show.json", "Uid", userId), IpetUser.class);

    }

    public IpetUserProfile getUserProfile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
