/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import com.ipet.android.sdk.api.domain.IpetUser;
import com.ipet.android.sdk.api.domain.IpetUserProfile;
import com.ipet.android.sdk.api.domain.IpetUserSettings;
import java.io.File;

/**
 *
 * @author xiaojinghai
 */
public interface UserOperations {

    /**
     * 更新用户基本信息
     *
     * @param user
     */
    public void updateUserInfo(IpetUser user);

    /**
     * 更新用户头像
     *
     * @param Picture
     */
    public void updateUserPicture(File Picture);

    /**
     * 更新用户设置
     *
     * @param settings
     */
    public void updateUserSettings(IpetUserSettings settings);

    /**
     * 修改密码
     *
     * @param oldP
     * @param newP
     */
    public void changePassword(String oldP, String newP);

    /**
     * 获取本人用户信息
     *
     * @return
     */
    public IpetUser getUserInfo();

    /**
     * 获取别人用户信息
     *
     * @return
     */
    public IpetUser getOtherUserInfo(long userId);

    /**
     * 获取用户档案
     *
     * @return
     */
    public IpetUserProfile getUserProfile();

}
