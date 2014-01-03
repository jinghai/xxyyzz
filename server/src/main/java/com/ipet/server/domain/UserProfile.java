/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author yneos
 */
@Entity
@Table(name = "ipet_user_profile")
public class UserProfile extends IdEntity implements Serializable {
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

    public UserProfile() {
    }

    public UserProfile(Long id) {
        this.id = id;
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
