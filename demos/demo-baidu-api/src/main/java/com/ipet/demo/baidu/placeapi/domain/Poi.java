/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poi {

    private String name;            //poi名称
    private Location location;      //位置
    private String address;         //poi地址信息
    private String telephone;       //poi电话信息
    private String uid;             //poi的唯一标示
    private PoiDetail detail_info;  //poi的扩展信息，仅当scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。

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
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
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

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the detail_info
     */
    public PoiDetail getDetail_info() {
        return detail_info;
    }

    /**
     * @param detail_info the detail_info to set
     */
    public void setDetail_info(PoiDetail detail_info) {
        this.detail_info = detail_info;
    }

}
