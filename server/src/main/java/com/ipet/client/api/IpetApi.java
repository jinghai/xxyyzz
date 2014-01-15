/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

/**
 * IpetApi 门面
 *
 * @author xiaojinghai
 */
public interface IpetApi {

    /**
     * 返回帐号API.
     *
     * @return AccountApi
     */
    public AccountApi getAccountApi();

    /**
     * 返回用户API.
     *
     * @return UserApi
     */
    public UserApi getUserApi();

    public String getCurrUserId();

}
