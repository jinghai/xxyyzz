package com.ipet.android.ui.manager;

import java.util.ArrayList;
import java.util.List;

import com.ipet.android.vo.Feed;

public class FeedManager {

	public static List<Feed> load(){
		List<Feed> list = new ArrayList<Feed>();
		for (int i = 0; i < 10; i++) {   
			Feed feed = new Feed();
			feed.setCreate_by("zhangsan"+i);
			feed.setCreate_at("下午 "+i+":00");
			feed.setContent("context"+i);
	    	list.add(feed); 
	    }   
		return list;
	}
	
	
	public static List<Feed> loadNew(){
		List<Feed> list = new ArrayList<Feed>();
		for (int i = 0; i < 2; i++) {   
			Feed feed = new Feed();
			feed.setCreate_by("kongchun"+i);
			feed.setCreate_at("现在 "+i+":00");
			feed.setContent("context"+i);
	    	list.add(feed); 
	    }   
		return list;
	}
}