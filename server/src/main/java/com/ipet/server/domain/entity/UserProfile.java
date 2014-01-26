/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipet.server.domain.IdEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户档案
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_user_profiles", indexes = {
    @Index(name = "ipet_user_profiles_userId", columnList = "userId")
})
public class UserProfile extends IdEntity implements Serializable {

    private String userId;

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
