package com.ipet.server.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "ipet_user")
public class User extends IdEntity implements Serializable {

    @JsonUnwrapped
    private Avatar avatar;

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

    /* @JsonIgnore
     @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
     @JoinColumn(name = "user_profile_id")
     private UserProfile userProfile;*/
    //明文密码,瞬时属性,加密时使用,不持久化
    @JsonIgnore
    @Transient
    private String plainPassword;

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
     * @return the avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}
