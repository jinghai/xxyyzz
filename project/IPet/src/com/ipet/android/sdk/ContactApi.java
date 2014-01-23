/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk;

import com.ipet.android.sdk.domain.IpetUser;
import java.util.List;

/**
 * 用户关系API
 *
 * @author xiaojinghai
 */
public interface ContactApi {

    /**
     * 获取我关注的人
     *
     * @return
     */
    public List<IpetUser> getFollowList();

    /**
     * 获取关注我的人
     *
     * @return
     */
    public List<IpetUser> getFollowerList();

    /**
     * 获取我的好友列表
     *
     * @return
     */
    public List<IpetUser> getFriendList();

    /**
     * 关注
     *
     * @param userId
     * @return
     */
    public Boolean follow(String userId);

    /**
     * 反关注
     *
     * @param userId
     * @return
     */
    public Boolean unfollow(String userId);
}
