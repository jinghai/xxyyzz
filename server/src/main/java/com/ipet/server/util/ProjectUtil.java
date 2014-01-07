/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author xiaojinghai
 */
public class ProjectUtil {

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getApplicationPath() {
        Resource resource = new ClassPathResource("./");
        String filePath = "";
        try {
            filePath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = filePath.substring(0, filePath.indexOf("target"));
        return filePath;
    }

    /**
     * 创建目录
     *
     * @param filePath
     */
    public static void createDir(String filePath) {
        File myFile = new File(filePath);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
    }

    public static void LocaleResourceTest() {
        LocalizedResourceHelper lrHalper = new LocalizedResourceHelper();
        // ① 获取对应美国的本地化文件资源
        Resource msg_us = lrHalper.findLocalizedResource("i18n/message", ".properties",
                Locale.US);
        // ② 获取对应中国大陆的本地化文件资源
        Resource msg_cn = lrHalper.findLocalizedResource("i18n/message", ".properties",
                Locale.CHINA);
        System.out.println("fileName(us):" + msg_us.getFilename());
        System.out.println("fileName(cn):" + msg_cn.getFilename());

        Resource res = new ClassPathResource("conf/file1.txt");
        // ① 指定文件资源对应的编码格式（UTF-8）
        EncodedResource encRes = new EncodedResource(res, "UTF-8");
        // ② 这样才能正确读取文件的内容，而不会出现乱码

    }
}
