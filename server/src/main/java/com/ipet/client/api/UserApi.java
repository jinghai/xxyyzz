/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.domain.IpetUserUpdate;
import java.io.File;
import java.util.List;
import org.springframework.core.io.FileSystemResource;

/**
 *
 * @author xiaojinghai
 */
public interface UserApi {

    /**
     * 获取单个用户信息
     *
     * @param userId
     * @return
     */
    public IpetUser getUser(String userId);

    /**
     * 获取多个用户信息
     *
     * @param ids
     * @return
     */
    public List<IpetUser> getUsers(String ids);

    /**
     * 更新用户基本信息
     *
     * @param update
     * @return
     */
    public IpetUser updateUserInfo(IpetUserUpdate update);

    /**
     * 更新用户头像
     *
     * @param avatarFile
     * @return
     */
    public IpetUser updateAvatar(FileSystemResource avatarFile);

    /**
     *
     * @param avatarFile
     * @return
     */
    public IpetUser updateAvatar(File avatarFile);

    /**
     * 更新用户头像
     *
     * @param avatarFilePath 文件完整路径
     * @return
     */
    public IpetUser updateAvatar(String avatarFilePath);

}
