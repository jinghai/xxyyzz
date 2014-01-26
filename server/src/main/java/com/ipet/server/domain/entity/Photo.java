/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ipet.server.domain.IdEntity;
import com.ipet.server.domain.Location;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 图片
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_photos", indexes = {
    @Index(name = "ipet_photos_createAt", columnList = "createAt"),
    @Index(name = "ipet_photos_updateAt", columnList = "updateAt")
})
public class Photo extends IdEntity implements Serializable {

    private String originalURL;

    private String smallURL;

    private String text;

    //位置
    @JsonUnwrapped
    private Location location;

    private String forwordFromId;

    private Integer commentCount;

    private Integer favorCount;

    private String userId;

    /**
     * @return the originalURL
     */
    public String getOriginalURL() {
        return originalURL;
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
        return smallURL;
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
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * @return the favorCount
     */
    public Integer getFavorCount() {
        return favorCount;
    }

    /**
     * @param favorCount the favorCount to set
     */
    public void setFavorCount(Integer favorCount) {
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

}
