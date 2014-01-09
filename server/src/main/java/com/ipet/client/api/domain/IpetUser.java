package com.ipet.client.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class IpetUser {

    //登录名称
    private String id;
    @JsonProperty("displayName")
    private String name;
    private String email;
    private String phone;
    //登录次数
    private Long loginNum;
    private String avatar32;
    private String avatar48;
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
     * @return the loginNum
     */
    public Long getLoginNum() {
        return loginNum;
    }

    /**
     * @param loginNum the loginNum to set
     */
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
