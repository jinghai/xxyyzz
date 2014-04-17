/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 位置
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private Float lat;          //纬度值
    private Float lng;          //经度值

    /**
     * @return the lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public float getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(float lng) {
        this.lng = lng;
    }
}
