/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ipet.server.domain.IdEntity;
import com.ipet.server.domain.Location;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 图片
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_photos", indexes = {
    @Index(name = "ipet_photos_createAt", columnList = "createAt"),
    @Index(name = "ipet_photos_updateAt", columnList = "updateAt"),
    @Index(name = "ipet_photos_forwordFromId", columnList = "forwordFromId"),
    @Index(name = "ipet_photos_userId", columnList = "userId")
})
public class Photo extends IdEntity implements Serializable {

    private String originalURL;

    private String smallURL;

    private String text;

    //位置
    @JsonUnwrapped
    private Location location;

    private String forwordFromId;

    @Column(columnDefinition = "int default 0")
    private Integer commentCount = 0;

    @Column(columnDefinition = "int default 0")
    private Integer favorCount = 0;

    private String userId;

    //@Transient
    private String userName;

    //@Transient
    private String avatar48;

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
        return avatar48;
    }

    /**
     * @param avatar48 the avatar48 to set
     */
    public void setAvatar48(String avatar48) {
        this.avatar48 = avatar48;
    }

}
