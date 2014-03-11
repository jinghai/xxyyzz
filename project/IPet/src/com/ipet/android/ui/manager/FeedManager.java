package com.ipet.android.ui.manager;

import java.util.ArrayList;
import java.util.List;

import com.ipet.android.sdk.domain.IpetPhoto;

public class FeedManager {

	public static List<IpetPhoto> list = null;

	public static List<IpetPhoto> load() {
		if (list == null) {
			list = new ArrayList<IpetPhoto>();
			for (int i = 0; i < 15; i++) {
				IpetPhoto feed = new IpetPhoto();
				feed.setUserName("zhangsan" + i);
				feed.setCreateAt("下午 " + i + ":00");
				feed.setText("context" + i);
				feed.setSmallURL("");
				list.add(feed);
			}
		}
		return list;
	}

	public static List<IpetPhoto> loadNew() {
		List<IpetPhoto> list = new ArrayList<IpetPhoto>();
		for (int i = 0; i < 2; i++) {
			IpetPhoto feed = new IpetPhoto();
			feed.setUserName("kongchun" + i);
			feed.setCreateAt("现在 " + i + ":00");
			feed.setText("context" + i);
			list.add(feed);
		}
		return list;
	}

	public static List<IpetPhoto> loadMore() {
		// TODO Auto-generated method stub
		List<IpetPhoto> list = new ArrayList<IpetPhoto>();
		for (int i = 0; i < 2; i++) {
			IpetPhoto feed = new IpetPhoto();
			feed.setUserName("old Feed" + i);
			feed.setCreateAt("前天 " + i + ":00");
			feed.setText("context" + i);
			list.add(feed);
		}
		return list;
	}
}
