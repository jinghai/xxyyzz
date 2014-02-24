/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 图片赞
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpetFavor {

    private String id;

    //攒的话
    private String text;
    //谁攒的
    private String userId;
    //谁攒的
    private String userName;
    //攒什么
    private String photoId;

    private String createAt;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the photoId
     */
    public String getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * @return the createAt
     */
    public String getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
