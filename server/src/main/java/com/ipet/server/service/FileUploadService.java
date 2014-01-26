/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.service;

import com.ipet.server.domain.entity.User;
import com.ipet.server.domain.UserState;
import com.ipet.server.repository.UserDao;
import com.ipet.server.util.ProjectUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author yneos
 */
@Component
@Transactional(readOnly = true)
public class FileUploadService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;
    //网站根目录下上传文件的基础目录
    private final String baseDir = "files";
    //网站根目录下上传文件的基础目录
    private final String tempDir = "temp";
    //允许的扩展名
    private List<String> allowPrefix = new ArrayList<String>() {
        {
            add("jpg");
            add("gif");
            add("png");
        }
    };

    /**
     * 上传头像
     *
     * @param file
     * @throws IOException
     */
    @Transactional(readOnly = false)
    public User uploadAvatar(MultipartFile file, String userId) throws IOException {
        String webAppPath = webApplicationContext.getServletContext().getRealPath("/");
        String originalFilename = file.getOriginalFilename();
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (StringUtils.isEmpty(prefix) || !allowPrefix.contains(prefix)) {
            throw new RuntimeException("被禁止的文件类型");
        }
        //临时文件目录
        String tempDirRealPath = webAppPath + tempDir;
        ProjectUtil.createDir(tempDirRealPath);
        //临时文件绝对路径
        String tempFile = tempDirRealPath + "/" + ProjectUtil.generateShortUUID() + "." + prefix;
        //上传目录
        String uploadDirRealPath = webAppPath + baseDir + "/" + userId + "/";
        ProjectUtil.createDir(uploadDirRealPath);
        //32*32相对地址
        String avatar32RelativeFile = baseDir + "/" + userId + "/" + ProjectUtil.generateShortUUID() + "_32." + prefix;
        //32*32绝对地址
        String avatar32RealFile = webAppPath + avatar32RelativeFile;
        //48*48相对地址
        String avatar48RelativeFile = baseDir + "/" + userId + "/" + ProjectUtil.generateShortUUID() + "_48." + prefix;
        //48*48绝对地址
        String avatar48RealFile = webAppPath + avatar48RelativeFile;

        uploadFile(tempFile, file);

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
            File f = new File(webAppPath + old32);
            f.delete();
        }
        String old48 = user.getAvatar48();
        if (!StringUtils.isEmpty(old48)) {
            File f = new File(webAppPath + old48);
            f.delete();
        }
        //更新记录
        user.setAvatar32(avatar32RelativeFile);
        user.setAvatar48(avatar48RelativeFile);
        this.getUserDao().save(user);
        return user;
    }

    private void uploadFile(String pathAndName, MultipartFile file) throws IOException {
        File f = new File(pathAndName);
        file.transferTo(f);
    }

    private void testFilePath() throws IOException {

        if (applicationContext != null) {
            System.out.println("ApplicationContext OK!");
        }
        if (webApplicationContext != null) {
            System.out.println("getWebApplicationContext OK!");
            System.out.println("getWebApplicationContext:"
                    + webApplicationContext.getServletContext().getRealPath("/")
                    + tempDir);
        }

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
