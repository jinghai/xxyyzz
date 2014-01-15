package com.ipet.client.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpetUser {

    //@JsonDeserialize(as = Long.class)
    private String id;
    //登录名称
    private String loginName;
    //显示名称
    private String displayName;
    private String email;
    private String phone;
    //32*32头像地址
    private String avatar32;
    //48*48头像地址
    private String avatar48;
    //登录次数
    private String loginNum;
    //拥有店铺的数量
    private String shopCount;
    //拥有APP的数量
    private String appCount;
    //粉丝数量
    private String followerCount;
    //互粉数量
    private String friendCount;
    //关注数量
    private String subscibeCount;
    //发布图片的数量
    private String photoCount;
    //赞别人的数量
    private String favorCount;
    //发表评论数量
    private String commentCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateAt;

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
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the avatar32
     */
    public String getAvatar32() {
        return avatar32;
    }

    /**
     * @param avatar32 the avatar32 to set
     */
    public void setAvatar32(String avatar32) {
        this.avatar32 = avatar32;
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

    /**
     * @return the loginNum
     */
    public String getLoginNum() {
        return loginNum;
    }

    /**
     * @param loginNum the loginNum to set
     */
    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * @return the shopCount
     */
    public String getShopCount() {
        return shopCount;
    }

    /**
     * @param shopCount the shopCount to set
     */
    public void setShopCount(String shopCount) {
        this.shopCount = shopCount;
    }

    /**
     * @return the appCount
     */
    public String getAppCount() {
        return appCount;
    }

    /**
     * @param appCount the appCount to set
     */
    public void setAppCount(String appCount) {
        this.appCount = appCount;
    }

    /**
     * @return the followerCount
     */
    public String getFollowerCount() {
        return followerCount;
    }

    /**
     * @param followerCount the followerCount to set
     */
    public void setFollowerCount(String followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * @return the friendCount
     */
    public String getFriendCount() {
        return friendCount;
    }

    /**
     * @param friendCount the friendCount to set
     */
    public void setFriendCount(String friendCount) {
        this.friendCount = friendCount;
    }

    /**
     * @return the subscibeCount
     */
    public String getSubscibeCount() {
        return subscibeCount;
    }

    /**
     * @param subscibeCount the subscibeCount to set
     */
    public void setSubscibeCount(String subscibeCount) {
        this.subscibeCount = subscibeCount;
    }

    /**
     * @return the photoCount
     */
    public String getPhotoCount() {
        return photoCount;
    }

    /**
     * @param photoCount the photoCount to set
     */
    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
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
     * @return the createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the updateAt
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt the updateAt to set
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
