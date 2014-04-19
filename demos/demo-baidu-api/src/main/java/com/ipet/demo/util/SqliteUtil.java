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
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Administrator
 */
public class SqliteUtil {

    private static final Logger logger = Logger.getLogger(SqliteUtil.class.getName());
    private static final String connectionStr = "jdbc:sqlite:target/place.db";
    private static final String driverClassName = "org.sqlite.JDBC";
    private static Connection conn;

    static {
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "无法找到sqlite-JDBC驱动", ex);
        }
        SQLiteConfig config = new SQLiteConfig();
        config.setEncoding(SQLiteConfig.Encoding.UTF8);//why error?
        try {
            conn = DriverManager.getConnection(connectionStr, config.toProperties());

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "无法创建数据库连接对象", ex);
        }

    }

    public static Connection getConnection() {
        return conn;
    }

    //执行更新
    public static void executeUpdate(String str) {
        boolean ret = true;
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

    /**
     * 数据备份到文件
     *
     * @param dataBaseFile 相对于项目根目录
     */
    public static void backupTo(String dataBaseFile) {
        try {
            conn.createStatement().executeUpdate("backup to " + dataBaseFile);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 从数据文件恢复
     *
     * @param dataBaseFile 相对于项目根目录
     */
    public static void restoreFrom(String dataBaseFile) {
        try {
            conn.createStatement().executeUpdate("restore from " + dataBaseFile);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String arge[]) {
        backupTo("target/place-bak.db");
        restoreFrom("target/place-bak.db");
    }

}
