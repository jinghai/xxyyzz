package com.ipet.server.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "ipet_user")
public class User extends IdEntity implements Serializable {

    //登录名称
    // JSR303 BeanValidator的校验规则
    @NotBlank
    @Column
    private String loginName;

    //登录密码
    @Column
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    private String phone;

    //@JsonUnwrapped
    //private Avatar avatar;
    @Column
    private String avatar12;

    @Column
    private String avatar32;

    @Column
    private String avatar48;

    @Column
    private String avatar64;

    //盐,加密算法时使用
    @Column
    private String salt;

    @Column()
    private String roles;

    @Column()
    private Integer userState;

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
     * @return the userState
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * @param userState the userState to set
     */
    public void setUserState(Integer userState) {
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

    /**
     * @return the avatar12
     */
    public String getAvatar12() {
        return avatar12;
    }

    /**
     * @param avatar12 the avatar12 to set
     */
    public void setAvatar12(String avatar12) {
        this.avatar12 = avatar12;
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
     * @return the avatar64
     */
    public String getAvatar64() {
        return avatar64;
    }

    /**
     * @param avatar64 the avatar64 to set
     */
    public void setAvatar64(String avatar64) {
        this.avatar64 = avatar64;
    }

}
