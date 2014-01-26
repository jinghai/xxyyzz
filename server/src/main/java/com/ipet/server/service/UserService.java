package com.ipet.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.domain.entity.User;
import com.ipet.server.domain.UserState;
import com.ipet.server.repository.UserDao;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理类.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileUploadService fileUploadService;

    public List<User> getAllUser() {
        return (List<User>) getUserDao().findAll();
    }

    public User getUserById(String userId) {
        User user = getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        return user;
    }

    public List<User> getUserByIds(List<String> ids) {
        List<User> users = getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        return users;
    }

    @Transactional(readOnly = false)
    public User updateUserAndAvatar(User userUpdate, MultipartFile file) throws IOException {
        User user = updateUserInfo(userUpdate);
        if (!file.isEmpty()) {
            fileUploadService.uploadAvatar(file, userUpdate.getId());
        }
        return user;
    }

    /**
     * 更新用户信息
     *
     * @param userUpdate
     * @param userId
     * @return
     */
    @Transactional(readOnly = false)
    public User updateUserInfo(User userUpdate) {
        User user = getUserById(userUpdate.getId());
        user.setDisplayName(userUpdate.getDisplayName());
        user.setEmail(userUpdate.getEmail());
        user.setPhone(userUpdate.getPhone());
        getUserDao().save(user);
        return user;
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

}
