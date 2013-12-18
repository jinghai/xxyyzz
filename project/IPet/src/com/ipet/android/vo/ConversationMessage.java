package com.ipet.android.vo;

public class ConversationMessage {
	private String id;
	private String name;
	private String avatar;
	private String content;
	private String created_at;
	private String new_msg_num;
	
	public String getNew_msg_num() {
		return new_msg_num;
	}
	public void setNew_msg_num(String new_msg_num) {
		this.new_msg_num = new_msg_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	

}
