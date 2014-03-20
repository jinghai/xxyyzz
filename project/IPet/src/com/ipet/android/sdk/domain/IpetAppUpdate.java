/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.domain;

/**
 * 应用升级信息
 *
 * @author xiaojinghai
 */
public class IpetAppUpdate {

    //数值版本号,用于版本更新比较
    private Integer versionCode;

    //字符串版本号，呈现给用户界面
    private String versionName;

    //app下载地址（完整路径）
    private String appDownloadUrl;

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
