/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api;

import com.ipet.android.sdk.api.domain.IpetUser;
import java.util.List;

/**
 *
 * @author xiaojinghai
 */
public interface ContactOperations {

    /**
     * 关注
     *
     * @param userId
     */
    public void subscribe(long userId);

    /**
     * 反关注
     *
     * @param userId
     */
    public void unSubscribe(long userId);

    /**
     * 获取我订阅的用户列表(关注)
     *
     * @param index 页号
     * @param size 页容量
     * @return
     */
    public List<IpetUser> getMySubscibes(int index, int size);

    /**
     * 获取我的粉丝列表(粉丝)
     *
     * @param index
     * @param size
     * @return
     */
    public List<IpetUser> getMyfollowers(int index, int size);

    /**
     * 获取互粉列表（朋友）
     *
     * @param index
     * @param size
     * @return
     */
    public List<IpetUser> getMyfriends(int index, int size);
}
