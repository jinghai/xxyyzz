/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.cache;

import org.springframework.util.Assert;

/**
 * 缓存实体
 *
 * @author xiao
 */
public class EtagCacheEntry {

    private String key;       //请求的URI
    private String value;     //返回值
    private String etag;
    private Long expireOn;   //过期时间戳（毫秒数）

    public EtagCacheEntry() {

    }

    public EtagCacheEntry(String key) {
        Assert.notNull(key);
        this.key = key;
    }

    public EtagCacheEntry(String key, String value, String etag, Long expireOn) {
        Assert.notNull(key);
        Assert.notNull(value);
        Assert.notNull(etag);
        Assert.notNull(expireOn);
        this.key = key;
        this.value = value;
        this.etag = etag;
        this.expireOn = expireOn;

    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     * @return the expireOn
     */
    public Long getExpireOn() {
        return expireOn;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param etag the etag to set
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * @param expireOn the expireOn to set
     */
    public void setExpireOn(Long expireOn) {
        this.expireOn = expireOn;
    }

}
