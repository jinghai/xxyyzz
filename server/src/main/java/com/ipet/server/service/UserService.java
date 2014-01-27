package com.ipet.server.service;

import com.ipet.server.app.AppConfig;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.domain.entity.User;
import com.ipet.server.domain.UserState;
import com.ipet.server.repository.UserDao;
import com.ipet.server.util.ProjectUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.StringUtils;
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
    private AppConfig appConfig;

    //允许上传的头像文件扩展名
    private final List<String> allowPrefix = new ArrayList<String>() {
        {
            add("jpg");
            add("gif");
            add("png");
        }
    };

    /**
     * 获取所有用户，仅限测试使用
     *
     * @return
     */
    public List<User> getAllUser() {
        return (List<User>) getUserDao().findAll();
    }

    /**
     * 按ID获取用户信息
     *
     * @param userId
     * @return
     */
    public User getUserById(String userId) {
        User user = getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        return user;
    }

    /**
     * 批量获取用户
     *
     * @param ids
     * @return
     */
    public List<User> getUserByIds(List<String> ids) {
        List<User> users = getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        return users;
    }

    /**
     * 更新头像
     *
     * @param userId
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = false)
    public User updateAvatar(String userId, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (StringUtils.isEmpty(prefix) || !allowPrefix.contains(prefix)) {
            throw new RuntimeException("被禁止的文件类型");
        }
        //临时文件目录
        String tempDirRealPath = appConfig.getTempDirRealPath();
        ProjectUtil.createDir(tempDirRealPath);
        //临时文件绝对路径
        String tempFile = tempDirRealPath + ProjectUtil.generateShortUUID() + "." + prefix;
        //上传目录
        String uploadDirRealPath = appConfig.getUploadDirRealPath() + userId + "/";
        ProjectUtil.createDir(uploadDirRealPath);
        //32*32相对地址
        String avatar32RelativeFile = appConfig.getUploadDir() + userId + "/" + ProjectUtil.generateShortUUID() + "_32." + prefix;
        //32*32绝对地址
        String avatar32RealFile = appConfig.getWebContextRealPath() + avatar32RelativeFile;
        //48*48相对地址
        String avatar48RelativeFile = appConfig.getUploadDir() + userId + "/" + ProjectUtil.generateShortUUID() + "_48." + prefix;
        //48*48绝对地址
        String avatar48RealFile = appConfig.getWebContextRealPath() + avatar48RelativeFile;

        //上传原始文件到临时目录
        File originalFile = new File(tempFile);
        file.transferTo(originalFile);

        //文件冲突检查,避免shorUUID可能存在重复的bug
        File avatar32File = new File(avatar32RealFile);
        File avatar48File = new File(avatar48RealFile);
        if (avatar32File.exists() || avatar48File.exists()) {
            throw new RuntimeException("文件已存在");
        }

        /*
         * 若图片横比32小，高比32小，不变
         * 若图片横比32小，高比32大，高缩小到32，图片比例不变
         * 若图片横比32大，高比32小，横缩小到32，图片比例不变
         * 若图片横比32大，高比32大，图片按比例缩小，横为32或高为32
         */
        Thumbnails.of(tempFile).size(32, 32).toFile(avatar32RealFile);
        Thumbnails.of(tempFile).size(48, 48).toFile(avatar48RealFile);

        //清除缓存
        File tFile = new File(tempFile);
        tFile.delete();

        User user = this.getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        //删除旧文件
        String old32 = user.getAvatar32();
        if (!StringUtils.isEmpty(old32)) {
            File f = new File(appConfig.getWebContextRealPath() + old32);
            f.delete();
        }
        String old48 = user.getAvatar48();
        if (!StringUtils.isEmpty(old48)) {
            File f = new File(appConfig.getWebContextRealPath() + old48);
            f.delete();
        }
        //更新记录
        user.setAvatar32(avatar32RelativeFile);
        user.setAvatar48(avatar48RelativeFile);
        this.getUserDao().save(user);
        return user;
    }

    /**
     * 更新用户基本信息
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
