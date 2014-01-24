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
 * 图片评论
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_comments")
public class Comment extends IdEntity implements Serializable {

    private Long userId;

    private Long photoId;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the photoId
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

}
