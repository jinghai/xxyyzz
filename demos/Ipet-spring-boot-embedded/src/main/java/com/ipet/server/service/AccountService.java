package com.ipet.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.domain.User;
import com.ipet.server.repository.UserDao;
import org.springframework.util.Assert;

@Component
@Transactional(readOnly = true)
public class AccountService {

    @Autowired
    private UserDao userDao;

    @Autowired
    public AccountService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return (List<User>) userDao.findAll();
    }

    public User getUser(Long id) {
        return userDao.findOne(id);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        Assert.notNull(user.getName(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");
        userDao.save(user);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
