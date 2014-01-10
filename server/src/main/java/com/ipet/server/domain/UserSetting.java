/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户档案
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_user_settings")
public class UserSetting extends IdEntity implements Serializable {

    @JsonIgnore
    @OneToOne(mappedBy = "userSetting", fetch = FetchType.LAZY)
    private User user;

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
