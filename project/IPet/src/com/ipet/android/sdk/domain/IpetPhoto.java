/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.domain;

import com.ipet.android.sdk.base.ApiContext;

/**
 *
 * @author xiaojinghai
 */
public class IpetPhoto {

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
}
