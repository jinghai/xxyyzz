/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain.entity;

import com.ipet.server.domain.IdEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 图片赞
 *
 * @author xiaojinghai
 */
@Entity
@Table(name = "ipet_favors", indexes = {
    @Index(name = "ipet_favors_userId", columnList = "userId"),
    @Index(name = "ipet_favors_photoId", columnList = "photoId"),})
public class Favor extends IdEntity implements Serializable {

    private String userId;

    private String photoId;

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

    /**
     * @return the photoId
     */
    public String getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

}
