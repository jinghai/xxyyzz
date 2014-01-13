package com.ipet.server.web.rest.base;

import com.ipet.server.web.rest.v1.AccountRestController;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yneos
 */
public class TestFilePath {

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Test
    public void Test() throws IOException {

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(TestFilePath.class.getClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(TestFilePath.class.getResource(""));

        System.out.println(TestFilePath.class.getResource("/"));//Class文件所在路径

        System.out.println(Thread.currentThread().getContextClassLoader().getResource("."));
        System.out.println(TestFilePath.class.getClassLoader().getResource("."));
        System.out.println(ClassLoader.getSystemResource("."));
        System.out.println(TestFilePath.class.getResource("."));//Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(new File("").getAbsolutePath());
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.timezone"));
        System.out.println(System.getProperty("user.language"));
        System.out.println(System.getProperty("user.country"));
        System.out.println(System.getProperty("java.io.tmpdir"));

        System.out.println(new FileSystemResource("").getURI().toString());
        System.out.println(new FileSystemResource("/").getURI().toString());

    }

    public static void main(String[] args) throws IOException {
        new TestFilePath().Test();
    }
}
