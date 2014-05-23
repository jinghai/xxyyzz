package com.ipet.android.sdk.impl;

import android.content.Context;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.domain.IpetUser;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.LinkedMultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class ContactApi extends ApiBase {

    public ContactApi(Context context) {
        super(context);
    }

    public List<IpetUser> getFollowList() {
        requireAuthorization();
        String uId = getCurrUserId();
        URI uri = buildUri("contact/listFollows", "uid", uId);
        IpetUser[] users = getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    public List<IpetUser> getFollowerList() {
        requireAuthorization();
        String uId = getCurrUserId();
        URI uri = buildUri("contact/listFollowers", "uid", uId);
        IpetUser[] users = getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    public List<IpetUser> getFriendList() {
        requireAuthorization();
        String uId = getCurrUserId();
        URI uri = buildUri("contact/listfriends", "uid", uId);
        IpetUser[] users = getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    public Boolean follow(String userId) {
        requireAuthorization();
        String uId = getCurrUserId();
        URI uri = buildUri("contact/follow");

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userIdA", uId);
        body.add("userIdB", userId);

        Boolean ret = getRestTemplate().postForObject(uri, body, Boolean.class);
        return ret;
    }

    public Boolean unfollow(String userId) {
        requireAuthorization();
        String uId = getCurrUserId();
        URI uri = buildUri("contact/unfollow");

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userIdA", uId);
        body.add("userIdB", userId);

        Boolean ret = getRestTemplate().postForObject(uri, body, Boolean.class);
        return ret;
    }

}
