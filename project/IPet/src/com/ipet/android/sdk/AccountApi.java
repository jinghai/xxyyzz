/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk;

import com.ipet.android.sdk.domain.IpetUser;

/**
 *
 * @author xiaojinghai
 */
public interface AccountApi {

    /**
     * 登入
     *
     * @param loginName
     * @param password
     * @return IpetUser
     */
    public IpetUser login(String loginName, String password);

    /**
     * 登出
     */
    public void logout();

    /**
     * 注册
     *
     * @param loginName
     * @param password
     * @return
     */
    public IpetUser register(String loginName, String password);

    /**
     * 检查登录名是否已存在
     *
     * @param loginName
     * @return
     */
    public Boolean checkLoginName(String loginName);

    /**
     * 检查Phone是否已存在
     *
     * @param phone
     * @return
     */
    public Boolean checkPhone(String phone);

    /**
     * 检查Email是否已存在
     *
     * @param email
     * @return
     */
    public Boolean checkEmail(String email);

    /**
     * 修改密码
     *
     * @param oldP 原密码
     * @param newP 新密码
     * @return
     */
    public Boolean changePassword(String oldP, String newP);

}
