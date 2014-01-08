/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.service;

import com.ipet.server.domain.User;
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
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author yneos
 */
@Component
public class FileUploadService {

    @Autowired
    AccountService accountService;

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
    public void uploadAvatar(MultipartFile file, long userId) throws IOException {
        String webAppPath = webApplicationContext.getServletContext().getRealPath("/");
        String originalFilename = file.getOriginalFilename();
        String prefix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (StringUtils.isEmpty(prefix) || !allowPrefix.contains(prefix)) {
            throw new RuntimeException("被禁止的文件类型");
        }
        String tempPathAndFile = tempDir + "/" + ProjectUtil.generateShortUUID() + "." + prefix;
        //相对网站根目录的磁盘路径
        String avatar32PathAndFile = baseDir + "/" + userId + ProjectUtil.generateShortUUID() + "_32." + prefix;
        String avatar48PathAndFile = baseDir + "/" + userId + ProjectUtil.generateShortUUID() + "_48." + prefix;
        uploadFile(webAppPath + tempPathAndFile, file);
        /*
         * 若图片横比32小，高比32小，不变
         * 若图片横比32小，高比32大，高缩小到32，图片比例不变
         * 若图片横比32大，高比32小，横缩小到32，图片比例不变
         * 若图片横比32大，高比32大，图片按比例缩小，横为32或高为32
         */
        Thumbnails.of(webAppPath + tempPathAndFile).size(32, 32).toFile(webAppPath + avatar32PathAndFile);
        Thumbnails.of(webAppPath + tempPathAndFile).size(48, 48).toFile(webAppPath + avatar48PathAndFile);
        User user = accountService.getUser(userId);
        user.setAvatar32(avatar32PathAndFile);
        user.setAvatar48(avatar48PathAndFile);
        //todo数据库处理,url处理
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
        System.out.println("currentThread:"
                + Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println("getClassLoader:"
                + FileUploadService.class.getClassLoader().getResource(""));
        System.out.println("ClassLoader:"
                + ClassLoader.getSystemResource(""));
        System.out.println("FileUploadService:"
                + FileUploadService.class.getResource(""));
        System.out.println("FileUploadService/:"
                + FileUploadService.class.getResource("/"));//Class文件所在路径
        System.out.println("File/:" + new File("/").getAbsolutePath());
        System.out.println("user.dir:" + System.getProperty("user.dir"));
        System.out.println("ClassPathResource:" + new ClassPathResource("./").getFile().getAbsolutePath());
        /* ApplicationContext OK! getWebApplicationContext OK!
         * getWebApplicationContext:D:\dev\KD\xxyyzz\server\target\server-1.0.0\/files
         * currentThread:file:/D:/dev/KD/xxyyzz/server/target/server-1.0.0/WEB-INF/classes/
         * getClassLoader:file:/D:/dev/KD/xxyyzz/server/target/server-1.0.0/WEB-INF/classes/
         * ClassLoader:null
         * FileUploadService:file:/D:/dev/KD/xxyyzz/server/target/server-1.0.0/WEB-INF/classes/com/ipet/server/service/
         * FileUploadService/:file:/D:/dev/KD/xxyyzz/server/target/server-1.0.0/WEB-INF/classes/
         * File/:E:\ user.dir:E:\RunTime\apache-tomcat-7.0.47\bin
         * ClassPathResource:D:\dev\KD\xxyyzz\server\target\server-1.0.0\WEB-INF\classes
         *
         */
    }

}
