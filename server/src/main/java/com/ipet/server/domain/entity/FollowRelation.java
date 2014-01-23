/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.ipet.server.domain.IdEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户关注关系表
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_follow_relations")
public class FollowRelation extends IdEntity implements Serializable {

    private Long userIdA;
    private Long userIdB;

    /**
     * @return the userIdA
     */
    public Long getUserIdA() {
        return userIdA;
    }

    /**
     * @param userIdA the userIdA to set
     */
    public void setUserIdA(Long userIdA) {
        this.userIdA = userIdA;
    }

    /**
     * @return the userIdB
     */
    public Long getUserIdB() {
        return userIdB;
    }

    /**
     * @param userIdB the userIdB to set
     */
    public void setUserIdB(Long userIdB) {
        this.userIdB = userIdB;
    }

}
