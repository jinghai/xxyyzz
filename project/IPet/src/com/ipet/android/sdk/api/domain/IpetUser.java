/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.domain;

/**
 *
 * @author xiaojinghai
 */
public class IpetUser {

    private Long id;
    private String name;
    private String displayName;
    private String secret;
    private String email;
    private String phone;
    //详细信息URL
    private String ProfileUrl;
    //头像URL
    private String pictureUrl;
    //关注数量
    private int subscibeCount;
    //粉丝数量
    private int followerCount;
    //互粉数量
    private int friendsCount;
    //最后一次的地理位置文字说明
    private String lastLocation;
    //创建时间
    private String createAt;
    //更新时间
    private String updateAt;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param nickname the displayName to set
     */
    public void setDisplayName(String nickname) {
        this.displayName = nickname;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
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
     * @return the pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @param pictureUrl the pictureUrl to set
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @return the subscibeCount
     */
    public int getSubscibeCount() {
        return subscibeCount;
    }

    /**
     * @param subscibeCount the subscibeCount to set
     */
    public void setSubscibeCount(int subscibeCount) {
        this.subscibeCount = subscibeCount;
    }

    /**
     * @return the followerCount
     */
    public int getFollowerCount() {
        return followerCount;
    }

    /**
     * @param followerCount the followerCount to set
     */
    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * @return the friendsCount
     */
    public int getFriendsCount() {
        return friendsCount;
    }

    /**
     * @param friendsCount the friendsCount to set
     */
    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * @return the lastLocation
     */
    public String getLastLocation() {
        return lastLocation;
    }

    /**
     * @param lastLocation the lastLocation to set
     */
    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
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
     * @return the updateAt
     */
    public String getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt the updateAt to set
     */
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return the ProfileUrl
     */
    public String getProfileUrl() {
        return ProfileUrl;
    }

    /**
     * @param ProfileUrl the ProfileUrl to set
     */
    public void setProfileUrl(String ProfileUrl) {
        this.ProfileUrl = ProfileUrl;
    }

}
