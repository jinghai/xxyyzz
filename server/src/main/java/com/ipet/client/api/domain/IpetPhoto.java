/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipet.client.api.base.ApiContext;

/**
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpetPhoto {

    private String id;
    //原图
    private String originalURL;
    //缩略图
    private String smallURL;
    //文字描述
    private String text;
    //转发自
    private String forwordFromId;
    //评论数量
    private String commentCount;
    //赞数量
    private String favorCount;
    //所属用户
    private String userId;

    private String userName;

    private String avatar48;

    private String createAt;

    /**
     * @return the originalURL
     */
    public String getOriginalURL() {
        return ApiContext.FILE_SERVER_BASE + originalURL;
    }

    /**
     * @param originalURL the originalURL to set
     */
    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    /**
     * @return the smallURL
     */
    public String getSmallURL() {
        return ApiContext.FILE_SERVER_BASE + smallURL;
    }

    /**
     * @param smallURL the smallURL to set
     */
    public void setSmallURL(String smallURL) {
        this.smallURL = smallURL;
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

    /**
     * @return the forwordFromId
     */
    public String getForwordFromId() {
        return forwordFromId;
    }

    /**
     * @param forwordFromId the forwordFromId to set
     */
    public void setForwordFromId(String forwordFromId) {
        this.forwordFromId = forwordFromId;
    }

    /**
     * @return the commentCount
     */
    public String getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * @return the favorCount
     */
    public String getFavorCount() {
        return favorCount;
    }

    /**
     * @param favorCount the favorCount to set
     */
    public void setFavorCount(String favorCount) {
        this.favorCount = favorCount;
    }

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
     * @return the avatar48
     */
    public String getAvatar48() {
        return ApiContext.FILE_SERVER_BASE + avatar48;
    }

    /**
     * @param avatar48 the avatar48 to set
     */
    public void setAvatar48(String avatar48) {
        this.avatar48 = avatar48;
    }
}
