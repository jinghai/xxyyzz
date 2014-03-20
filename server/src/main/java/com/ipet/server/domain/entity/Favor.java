/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.ipet.server.domain.IdEntity;

/**
 * 图片赞
 * 
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_favors", indexes = { @Index(name = "ipet_favors_userId", columnList = "userId"),
		@Index(name = "ipet_favors_photoId", columnList = "photoId") })
public class Favor extends IdEntity implements Serializable {

	// 攒的话
	private String text;
	// 谁攒的
	private String userId;
	// 谁攒的
	private String userName;
	// 攒什么
	private String photoId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId
	 *            the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
