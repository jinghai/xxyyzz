/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.client.api.impl;

import com.ipet.client.api.ContactApi;
import com.ipet.client.api.UserApi;
import com.ipet.client.api.base.ApiBase;
import com.ipet.client.api.base.ApiContext;
import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.domain.IpetUserUpdate;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;

/**
 *
 * @author yneos
 */
public class ContactApiImpl extends ApiBase implements ContactApi {

    public ContactApiImpl(ApiContext context) {
        super(context);
    }

    @Override
    public List<IpetUser> getFollowList() {
        requireAuthorization();
        String uId = context.getCurrUserId();
        URI uri = buildUri("contact/listFollows", "uid", uId);
        IpetUser[] users = context.getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    @Override
    public List<IpetUser> getFollowerList() {
        requireAuthorization();
        String uId = context.getCurrUserId();
        URI uri = buildUri("contact/listFollowers", "uid", uId);
        IpetUser[] users = context.getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    @Override
    public List<IpetUser> getFriendList() {
        requireAuthorization();
        String uId = context.getCurrUserId();
        URI uri = buildUri("contact/listfriends", "uid", uId);
        IpetUser[] users = context.getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    @Override
    public Boolean follow(String userId) {
        requireAuthorization();
        String uId = context.getCurrUserId();
        URI uri = buildUri("contact/follow");

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userIdA", uId);
        body.add("userIdB", userId);

        Boolean ret = context.getRestTemplate().postForObject(uri, body, Boolean.class);
        return ret;
    }

    @Override
    public Boolean unfollow(String userId) {
        requireAuthorization();
        String uId = context.getCurrUserId();
        URI uri = buildUri("contact/unfollow");

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userIdA", uId);
        body.add("userIdB", userId);

        Boolean ret = context.getRestTemplate().postForObject(uri, body, Boolean.class);
        return ret;

    }

}
