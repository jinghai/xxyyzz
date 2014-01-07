/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.service;

import com.ipet.server.util.ProjectUtil;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author yneos
 */
@Component
public class FileUploadService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;
    //网站根目录下上传文件的基础目录
    private String uplodBaseDir = "files";

    /**
     * 上传头像
     *
     * @param name
     * @param file
     * @throws IOException
     */
    public void uploadAvatar(String name, MultipartFile file) throws IOException {
        //name为空时使用原始名称
        boolean useOriginalFilename = name == null;
        String fileName = useOriginalFilename ? file.getOriginalFilename() : name;
        String path = getWebApplicationContext().getServletContext().getRealPath("/") + uplodBaseDir;
        ProjectUtil.createDir(path);
        file.transferTo(new File(path + "/" + fileName));
        //todo图片处理：http://iaiai.iteye.com/blog/1549726
        //todo数据库处理,url处理 
        /**
         * if (getApplicationContext() != null) {
         * System.out.println("ApplicationContext OK!"); } if
         * (getWebApplicationContext() != null) {
         * System.out.println("getWebApplicationContext OK!");
         * System.out.println("getWebApplicationContext:" +
         * getWebApplicationContext().getServletContext().getRealPath("/") +
         * uplodDir); } System.out.println("currentThread:" +
         * Thread.currentThread().getContextClassLoader().getResource(""));
         * System.out.println("getClassLoader:" +
         * FileUploadService.class.getClassLoader().getResource(""));
         * System.out.println("ClassLoader:" +
         * ClassLoader.getSystemResource(""));
         * System.out.println("FileUploadService:" +
         * FileUploadService.class.getResource(""));
         * System.out.println("FileUploadService/:" +
         * FileUploadService.class.getResource("/"));//Class文件所在路径
         * System.out.println("File/:" + new File("/").getAbsolutePath());
         * System.out.println("user.dir:" + System.getProperty("user.dir"));
         * System.out.println("ClassPathResource:" + new
         * ClassPathResource("./").getFile().getAbsolutePath());
         * ApplicationContext OK! getWebApplicationContext OK!
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

    /**
     * @return the applicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param applicationContext the applicationContext to set
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @return the webApplicationContext
     */
    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    /**
     * @param webApplicationContext the webApplicationContext to set
     */
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

}
