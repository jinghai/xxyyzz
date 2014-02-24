package com.ipet.server.service;

import com.ipet.server.domain.UserState;
import com.ipet.server.domain.entity.Favor;
import com.ipet.server.domain.entity.Photo;
import com.ipet.server.domain.entity.User;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.FavorDao;
import com.ipet.server.repository.PhotoDao;
import com.ipet.server.repository.UserDao;

/**
 * 攒服务.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class FavorService {

    private static Logger logger = LoggerFactory.getLogger(FavorService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private FavorDao favorDao;
    @Autowired
    private PhotoDao photoDao;

    public List<Favor> getFavorList(String photoId) {
        return getFavorDao().findByPhotoId(photoId);
    }

    @Transactional(readOnly = false)
    public Favor favor(String photoId, String userId, String text) {
        User user = getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        if (user == null) {
            throw new RuntimeException("无效用户");
        }

        Favor favor = new Favor();
        favor.setText(text);
        favor.setPhotoId(photoId);
        favor.setUserName(user.getDisplayName());
        favor.setUserId(userId);
        getFavorDao().save(favor);

        Photo photo = getPhotoDao().findOne(photoId);
        photo.setFavorCount(photo.getFavorCount() + 1);
        getPhotoDao().save(photo);

        user.setFavorCount(user.getFavorCount() + 1);
        getUserDao().save(user);
        return favor;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the favorDao
     */
    public FavorDao getFavorDao() {
        return favorDao;
    }

    /**
     * @param favorDao the favorDao to set
     */
    public void setFavorDao(FavorDao favorDao) {
        this.favorDao = favorDao;
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
