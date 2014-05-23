package com.ipet.android.sdk;

import android.content.Context;

import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetUser;

import java.net.URI;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class AccountApi extends ApiBase {

    public AccountApi(Context context) {
        super(context);
    }

    public IpetUser login(String loginName, String password) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", loginName);
        request.add("password", password);
        IpetUser user = getRestTemplate().postForObject(buildUri("account/login"), request, IpetUser.class);
        setIsAuthorized(true);
        setCurrUserId(user.getId());
        return user;
    }

    public void logout() {
        setIsAuthorized(Boolean.FALSE);
        setCurrUserId("");
    }

    public IpetUser register(String loginName, String password) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", loginName);
        request.add("password", password);
        IpetUser user = getRestTemplate().postForObject(buildUri("account/create"), request, IpetUser.class);
        return user;
    }

    public Boolean checkLoginName(String userName) {
        URI uri = buildUri("account/availableUsername", "username", userName);
        Boolean ret = getRestTemplate().getForObject(uri, Boolean.class);
        return ret;
    }

    public Boolean checkPhone(String phone) {
        URI uri = buildUri("account/availablePhone", "phone", phone);
        Boolean ret = getRestTemplate().getForObject(uri, Boolean.class);
        return ret;
    }

    public Boolean checkEmail(String email) {
        URI uri = buildUri("account/availableEmail", "email", email);
        Boolean ret = getRestTemplate().getForObject(uri, Boolean.class);
        return ret;
    }

    public Boolean changePassword(String oldP, String newP) {
        requireAuthorization();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("userId", getCurrUserId());
        body.add("oldPassword", oldP);
        body.add("newPassword", newP);
        Boolean ret = getRestTemplate().postForObject(buildUri("account/changePassword"), body, Boolean.class);
        return ret;
    }

}
