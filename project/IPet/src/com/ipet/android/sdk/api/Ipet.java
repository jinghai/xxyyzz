/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestOperations;

/**
 * Ipet SDK 门面
 *
 * @author xiaojinghai
 */
public interface Ipet extends ApiBinding {

    /**
     * 返回帐号API.
     *
     * @return AccountOperations
     */
    AccountOperations accountOperations();

    /**
     * 返回人脉API.
     *
     * @return ContactOperations
     */
    ContactOperations contactOperations();

    /**
     * 返回发现API.
     *
     * @return DiscoverOperations
     */
    DiscoverOperations discoverOperations();

    /**
     * 返回图片API.
     *
     * @return PhotoOperations
     */
    PhotoOperations photoOperations();

    /**
     * 返回用户API.
     *
     * @return UserOperations
     */
    UserOperations userOperations();

    /**
     * 返回公共Rest操作.
     *
     * @return RestOperations
     */
    RestOperations restOperations();

}
