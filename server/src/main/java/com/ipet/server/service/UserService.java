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
    
    public List<User> getAllUser() {
        return (List<User>) getUserDao().findAll();
    }
    
    public User getUserById(Long userId) {
        User user = getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        return user;
    }
    
    public List<User> getUserByIds(List<Long> ids) {
        List<User> users = getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        return users;
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
