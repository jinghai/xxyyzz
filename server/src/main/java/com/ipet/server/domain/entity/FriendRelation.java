/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.ipet.server.domain.IdEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户关注关系表
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_friend_relations", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userIdA", "userIdB"})
})
public class FriendRelation extends IdEntity implements Serializable {

    private String userIdA;
    private String userIdB;

    /**
     * @return the userIdA
     */
    public String getUserIdA() {
        return userIdA;
    }

    /**
     * @param userIdA the userIdA to set
     */
    public void setUserIdA(String userIdA) {
        this.userIdA = userIdA;
    }

    /**
     * @return the userIdB
     */
    public String getUserIdB() {
        return userIdB;
    }

    /**
     * @param userIdB the userIdB to set
     */
    public void setUserIdB(String userIdB) {
        this.userIdB = userIdB;
    }

}
