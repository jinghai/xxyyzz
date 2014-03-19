/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.ipet.server.domain.AppType;
import com.ipet.server.domain.IdEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 应用
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_apps", indexes = {
    @Index(name = "ipet_apps_userId", columnList = "userId")
})
public class App extends IdEntity implements Serializable {

    //应用名称
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private AppType type;

    @Column(unique = true)
    private String appKey;

    private String appSecret;

    private String userId;

    private String userName;

    //数值版本号,用于版本更新比较
    private Integer versionCode;

    //字符串版本号，呈现给用户界面
    private String versionName;

    //app下载地址（完整路径）
    @Column(length = 500)
    private String appDownloadUrl;

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
     * @return the type
     */
    public AppType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AppType type) {
        this.type = type;
    }

    /**
     * @return the appKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * @param appKey the appKey to set
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * @return the appSecret
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * @param appSecret the appSecret to set
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
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
     * @return the versionCode
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     * @param versionCode the versionCode to set
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return the versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * @param versionName the versionName to set
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * @return the appDownloadUrl
     */
    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    /**
     * @param appDownloadUrl the appDownloadUrl to set
     */
    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

}
