/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * 位置
 *
 * @author xiaojinghai
 *
 */
@Embeddable
public class Location implements Serializable {

    /**
     * 精度
     */
    private long longitude;

    /**
     * 纬度
     */
    private long latitude;

    /**
     * GEO哈希
     */
    private String geoHash;

    /**
     * 文本地址
     */
    private String address;

    /**
     * @return the longitude
     */
    public long getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public long getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the geoHash
     */
    public String getGeoHash() {
        return geoHash;
    }

    /**
     * @param geoHash the geoHash to set
     */
    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
