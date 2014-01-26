/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ipet.server.domain.IdEntity;
import com.ipet.server.domain.Location;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 店铺
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_shops")
public class Shop extends IdEntity implements Serializable {

    //店铺名称
    private String name;

    //位置
    @JsonUnwrapped
    private Location location;

    private String userId;

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

}
