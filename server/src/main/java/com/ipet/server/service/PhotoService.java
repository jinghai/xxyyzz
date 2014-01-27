package com.ipet.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.PhotoDao;

/**
 * 图片发布.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private PhotoDao photoDao;

    /**
     * @return the photoDao
     */
    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    /**
     * @param photoDao the photoDao to set
     */
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

}
