/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yneos
 */
public class GetConnectionTest {

    public static void main(String[] args) {
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("com.mysql.jdbc.Driver:OK!");

            String url = "jdbc:mysql://localhost:3306/ipet?useUnicode=true&amp;characterEncoding=UTF-8";    //JDBC的URL    
            Connection conn;

            conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("jdbc:mysql://localhost:3306/ipet:OK!");

            String sql = "select 1 from dual";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
            System.out.println("a");
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
