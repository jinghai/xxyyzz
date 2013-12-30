/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import com.ipet.android.sdk.api.domain.IpetUser;

/**
 *
 * @author xiaojinghai
 */
public interface AccountOperations {

    /**
     * 登入
     *
     * @param account
     * @param password
     * @return 返回accessToken
     */
    public String login(String account, String password);

    /**
     * 登出
     */
    public void logout();

    /**
     * 注册
     *
     * @param user
     */
    public void register(IpetUser user);

}
