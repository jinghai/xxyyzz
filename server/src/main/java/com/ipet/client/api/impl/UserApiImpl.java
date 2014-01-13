/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.impl;

import com.ipet.client.api.UserApi;
import com.ipet.client.api.base.ApiBase;
import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.domain.IpetUserUpdate;
import java.io.File;

/**
 *
 * @author yneos
 */
public class UserApiImpl extends ApiBase implements UserApi {

    public UserApiImpl(String appKey, String appSecret) {
        super(appKey, appSecret);
    }

    @Override
    public IpetUser getUser(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpetUser getUsers(String ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpetUser updateUserInfo(IpetUserUpdate update) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpetUser updateAvatar(IpetUserUpdate update) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpetUser update(IpetUserUpdate update, File avatarFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
