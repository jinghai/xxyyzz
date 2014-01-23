/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.impl;

import com.ipet.android.sdk.ContactApi;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.base.ApiContext;
import com.ipet.android.sdk.domain.IpetUser;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
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
