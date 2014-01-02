package com.ipet.android.ui.manager;

import com.ipet.android.sdk.api.domain.IpetUser;

public class UserManager {

	public static IpetUser getCurrentUser(){
		IpetUser user = new IpetUser();
		user.setId(1L);
		user.setName("孔纯");
		user.setFeedCount("114");
		user.setFollowerCount("120");
		user.setFriendsCount("12");
		user.setSubscibeCount("11");
		user.setPhone("139xxxxxxx7");
		user.setEmail("kcrens@gmail.com");
		user.setDisplayName("天堂龙");
		user.setPictureUrl("http://weibo.kedacom.com/weibo/files/share/photos/portrait/1/4/head_64.jpg?t=1367977581860");
		return user;
	}
	
	

}
