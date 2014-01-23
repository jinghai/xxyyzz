package com.ipet.android.ui.manager;

import com.ipet.android.sdk.domain.IpetUser;

public class UserManager {

    public static IpetUser getCurrentUser() {
        IpetUser user = new IpetUser();
        user.setId("1");
        user.setLoginName("kongchun");
        user.setDisplayName("孔纯");
        user.setPhotoCount("114");
        user.setFollowerCount("120");
        user.setFriendCount("12");
        user.setFollowCount("11");
        user.setPhone("139xxxxxxx7");
        user.setEmail("kcrens@gmail.com");
        user.setDisplayName("天堂龙");
        user.setAvatar32("http://weibo.kedacom.com/weibo/files/share/photos/portrait/1/4/head_64.jpg?t=1367977581860");
        user.setAvatar48("http://weibo.kedacom.com/weibo/files/share/photos/portrait/1/4/head_64.jpg?t=1367977581860");
        return user;
    }

}
