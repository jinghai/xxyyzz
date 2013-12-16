package com.ipet.android.ui.manager;

import java.util.ArrayList;
import java.util.List;

import com.ipet.R;
import com.ipet.android.vo.ConversationMessage;

public class ConversationManager {
	public static List<ConversationMessage> load() {
		List<ConversationMessage> list = new ArrayList<ConversationMessage>();
    	ConversationMessage msg = new ConversationMessage();
    	msg.setId("A");
    	//msg.setAvatar("http://weibo.kedacom.com/weibo/files/share/photos/portrait/1/4/head_64.jpg?t=1367977581860");
    	msg.setName("Pet秘书");
    	msg.setContent("测试消息");
    	msg.setCreated_at("今天");
		list.add(msg); 
		return list;
	}
	
	public static List<ConversationMessage> loadMore(String id) {
		List<ConversationMessage> list = new ArrayList<ConversationMessage>();
        for (int i = 0; i < 5; i++) {   

        	ConversationMessage msg = new ConversationMessage();
        	msg.setId(Integer.toString(i));
        	msg.setName("小秘书"+i);
        	msg.setContent("测试消息"+i);
        	msg.setCreated_at("昨天");
        	if(i==2){
        		msg.setNew_msg_num("2");
        	}
    		list.add(msg); 
        }   
		return list;
	}
}
