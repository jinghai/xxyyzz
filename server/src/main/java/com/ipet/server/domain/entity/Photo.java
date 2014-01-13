/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ipet.server.domain.IdEntity;
import com.ipet.server.domain.Location;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 图片
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_photos")
public class Photo extends IdEntity implements Serializable {

    private String originalURL;

    private String smallURL;

    private String text;

    //位置
    @JsonUnwrapped
    private Location location;

    //@JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "forword_id")
    private Photo forwordFrom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photo", fetch = FetchType.LAZY)
    private List<Comment> comments;

    private Integer commentCount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photo", fetch = FetchType.LAZY)
    private List<Favor> favors;

    private Integer favorCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

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
     * @return the forwordFrom
     */
    public Photo getForwordFrom() {
        return forwordFrom;
    }

    /**
     * @param forwordFrom the forwordFrom to set
     */
    public void setForwordFrom(Photo forwordFrom) {
        this.forwordFrom = forwordFrom;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
     * @return the favors
     */
    public List<Favor> getFavors() {
        return favors;
    }

    /**
     * @param favors the favors to set
     */
    public void setFavors(List<Favor> favors) {
        this.favors = favors;
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

}
