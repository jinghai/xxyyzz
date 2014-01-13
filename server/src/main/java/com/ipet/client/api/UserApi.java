/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.domain.IpetUserUpdate;
import java.io.File;

/**
 *
 * @author xiaojinghai
 */
public interface UserApi {

    public IpetUser getUser(String userId);

    public IpetUser getUsers(String ids);

    public IpetUser updateUserInfo(IpetUserUpdate update);

    public IpetUser updateAvatar(IpetUserUpdate update);

    public IpetUser update(IpetUserUpdate update, File avatarFile);

}
