/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.impl;

import com.ipet.android.sdk.AccountApi;
import com.ipet.android.sdk.IpetApi;
import com.ipet.android.sdk.UserApi;
import com.ipet.android.sdk.base.ApiContext;

/**
 * API门户，负载组装API组件，对用户提供统一访问接口
 *
 * @author xiaojinghai
 */
public class IpetApiImpl implements IpetApi {

    private static IpetApi instance;

    private final ApiContext context;

    private final AccountApi accountApi;

    private final UserApi userApi;

    private IpetApiImpl(String appKey, String appSecret) {
        context = ApiContext.getInstace(appKey, appSecret);
        accountApi = new AccountApiImpl(context);
        userApi = new UserApiImpl(context);
    }

    public static synchronized IpetApi getInstance(String appKey, String appSecret) {
        if (instance == null) {
            instance = new IpetApiImpl(appKey, appSecret);
        }
        return instance;
    }

    @Override
    public AccountApi getAccountApi() {
        return accountApi;
    }

    @Override
    public UserApi getUserApi() {
        return userApi;
    }

    @Override
    public String getCurrUserId() {
        return context.getCurrUserId();
    }

}
