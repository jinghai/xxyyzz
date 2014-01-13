package com.ipet.server.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipet.server.domain.IdEntity;
import com.ipet.server.domain.UserState;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

@Entity
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "ipet_users")
public class User extends IdEntity implements Serializable {

    // @JsonUnwrapped
    //private Avatar avatar;
    //登录名称
    // JSR303 BeanValidator的校验规则
    @NotBlank
    @Column(length = 50)
    private String loginName;

    @Column(length = 50)
    private String displayName;

    //登录密码
    @JsonIgnore
    @Column(length = 50)
    private String password;

    @Column(unique = true, length = 50)
    @Email()
    private String email;

    @Column(unique = true, length = 15)
    private String phone;

    //盐,加密算法时使用
    @JsonIgnore
    @Column(length = 50)
    private String salt;

    @Column()
    @JsonIgnore
    private String roles;

    @JsonIgnore
    @Column()
    @Enumerated(EnumType.ORDINAL)
    private UserState userState;

    //登录次数
    @Column()
    private Long loginNum;

    @Column
    private String avatar32;

    @Column
    private String avatar48;

    //明文密码,瞬时属性,加密时使用,不持久化
    @JsonIgnore
    @Transient
    private String plainPassword;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "setting_id")
    private UserSetting userSetting;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_location_id")
    private LastLocation lastLocation;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Shop> shops;

    private Integer shopCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<App> apps;

    private Integer appCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Follower> followers;

    private Integer followerCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Friend> friends;

    private Integer friendCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Follow> subscibes;

    private Integer subscibeCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Photo> photos;

    private Integer photoCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Favor> favors;

    private Integer favorCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    private Integer commentCount;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public Long getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Long loginNum) {
        this.loginNum = loginNum;
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
     * @return the userProfile
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * @param userProfile the userProfile to set
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * @return the userSetting
     */
    public UserSetting getUserSetting() {
        return userSetting;
    }

    /**
     * @param userSetting the userSetting to set
     */
    public void setUserSetting(UserSetting userSetting) {
        this.userSetting = userSetting;
    }

    /**
     * @return the lastLocation
     */
    public LastLocation getLastLocation() {
        return lastLocation;
    }

    /**
     * @param lastLocation the lastLocation to set
     */
    public void setLastLocation(LastLocation lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     * @return the shops
     */
    public List<Shop> getShops() {
        return shops;
    }

    /**
     * @param shops the shops to set
     */
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    /**
     * @return the shopCount
     */
    public Integer getShopCount() {
        return shopCount;
    }

    /**
     * @param shopCount the shopCount to set
     */
    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }

    /**
     * @return the apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * @param apps the apps to set
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    /**
     * @return the appCount
     */
    public Integer getAppCount() {
        return appCount;
    }

    /**
     * @param appCount the appCount to set
     */
    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    /**
     * @return the followers
     */
    public List<Follower> getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    /**
     * @return the followerCount
     */
    public Integer getFollowerCount() {
        return followerCount;
    }

    /**
     * @param followerCount the followerCount to set
     */
    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * @return the friends
     */
    public List<Friend> getFriends() {
        return friends;
    }

    /**
     * @param friends the friends to set
     */
    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    /**
     * @return the friendCount
     */
    public Integer getFriendCount() {
        return friendCount;
    }

    /**
     * @param friendCount the friendCount to set
     */
    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    /**
     * @return the subscibes
     */
    public List<Follow> getSubscibes() {
        return subscibes;
    }

    /**
     * @param subscibes the subscibes to set
     */
    public void setSubscibes(List<Follow> subscibes) {
        this.subscibes = subscibes;
    }

    /**
     * @return the subscibeCount
     */
    public Integer getSubscibeCount() {
        return subscibeCount;
    }

    /**
     * @param subscibeCount the subscibeCount to set
     */
    public void setSubscibeCount(Integer subscibeCount) {
        this.subscibeCount = subscibeCount;
    }

    /**
     * @return the photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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
     * @return the photoCount
     */
    public Integer getPhotoCount() {
        return photoCount;
    }

    /**
     * @param photoCount the photoCount to set
     */
    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
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

}
