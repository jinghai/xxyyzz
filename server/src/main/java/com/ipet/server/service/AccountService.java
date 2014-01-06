package com.ipet.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.domain.User;
import com.ipet.server.domain.UserRole;
import com.ipet.server.domain.UserState;
import com.ipet.server.repository.UserDao;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

/**
 * 用户管理类.
 *
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class AccountService {

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserDao userDao;

    public List<User> getAllUser() {
        return (List<User>) getUserDao().findAll();
    }

    public User getUser(Long id) {
        return getUserDao().findOne(id);
    }

    /**
     * 用户名是否已存在
     *
     * @param loginName
     * @return
     */
    public Boolean availableUsername(String loginName) {
        Long userId = getUserDao().getUserIdByLoginNameAndUserState(loginName, UserState.ENABLE);
        return userId == null;
    }

    /**
     * 电话号码是否已存在
     *
     * @param phone
     * @return
     */
    public Boolean availablePhone(String phone) {
        Long userId = getUserDao().getUserIdByPhoneAndUserState(phone, UserState.ENABLE);
        return userId == null;
    }

    /**
     * 验证Email是否已存在
     *
     * @param email
     * @return
     */
    public Boolean availableEmail(String email) {
        Long userId = getUserDao().getUserIdByEmailAndUserState(email, UserState.ENABLE);
        return userId == null;
    }

    /**
     * 是否第一次使用系统
     *
     * @param userId
     * @return
     */
    public Boolean isNewUser(Long userId) {
        Long t = getUserDao().getLoginNumByIdAndUserState(userId, UserState.ENABLE);
        if (null == t) {
            return false;
        }
        return 0l == t;
    }

    @Transactional(readOnly = false)
    public void registerUser(User user) {
        entryptPassword(user);
        user.setUserState(UserState.ENABLE);
        user.setRoles(UserRole.ENDUSER.name());
        user.setLoginNum(0l);
        //user.setUserProfile(new UserProfile());
        getUserDao().save(user);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDao().save(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(Long id) {

        getUserDao().delete(id);

    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
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
