/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class H2Util {

    private static final Logger logger = Logger.getLogger(H2Util.class.getName());
    private static final String connectionStr = "jdbc:h2:file:target/place";
    private static final String driverClassName = "org.h2.Driver";
    private static Connection conn;

    static {
        try {
            // load the JDBC driver using the current class loader
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "无法找到H2-JDBC驱动", ex);
        }

        try {
            java.util.Properties config = new java.util.Properties();
            config.setProperty("encoding", "UTF-8");
            conn = DriverManager.getConnection(connectionStr, config);

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "无法创建数据库连接对象", ex);
        }

    }

    public static Connection getConnection() {
        return conn;
    }

    //执行更新
    public static void executeUpdate(String str) {

        try {
            conn.prepareStatement(str).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqliteUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //执行查询
    public static ResultSet executeQuery(String str) {
        ResultSet ret = null;
        try {
            ret = conn.prepareStatement(str).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SqliteUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public static void main(String arge[]) {

    }
}
