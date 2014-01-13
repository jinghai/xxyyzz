/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.impl;

import com.ipet.client.api.AccountApi;
import com.ipet.client.api.base.ApiBase;
import com.ipet.client.api.domain.IpetUser;
import com.ipet.server.domain.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author yneos
 */
public class AccountApiImpl extends ApiBase implements AccountApi {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountApiImpl.class);

    private final String apiUrl = this.getApiHost() + "account/";

    public AccountApiImpl(String appKey, String appSecret) {
        super(appKey, appSecret);
    }

    @Override
    public String login(String loginName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpetUser register(String loginName, String password) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", loginName);
        request.add("password", password);
        IpetUser user = this.getRestTemplate().postForObject(apiUrl + "/create", request, IpetUser.class);
        logger.debug(user.toString());
        return user;
    }

    @Override
    public Boolean checkLoginName(String loginName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkPhone(String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean changePassword(String oldP, String newP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
