package com.ipet.server.service;

import com.ipet.server.domain.entity.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.PhotoDao;
import com.ipet.server.util.ProjectUtil;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 图片发布.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class DiscoverService {

    private static final Logger logger = LoggerFactory.getLogger(DiscoverService.class);

    @Autowired
    private PhotoDao photoDao;

    /**
     * 分页获取时间点之后最新的图片（发现）
     *
     * @param date
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<Photo> getNewerPhotoForPage(Date date, Integer pageNumber, Integer pageSize) {

        PageRequest pageR = ProjectUtil.buildPageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createAt");

        Page<Photo> ret = getPhotoDao().findByCreateAtBefore(date, pageR);
        return ret.getContent();
    }

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
