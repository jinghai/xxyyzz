package com.ipet.server.app;

import java.util.List;

import javax.sql.DataSource;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.validation.ValidationErrorMode;
import com.googlecode.flyway.core.validation.ValidationMode;

/**
 * database Flyway版本管理服务类
 */
public class DatabaseFlywayMigration {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);

        List<?> history = flyway.history();
        // logger.info("flyway history size : " + history.size());
        if (history == null || history.size() == 0) {
            flyway.init();
        }
		// flyway.setSchemas("win"); // 设置接受flyway进行版本管理的多个数据库
        // flyway.setTable("schema_version"); // 设置存放flyway metadata数据的表名

        flyway.setLocations("flyway"); // 设置flyway扫描sql升级脚本、java升级脚本的目录路径或包路径
        flyway.setEncoding("UTF-8"); // 设置sql脚本文件的编码

        flyway.setValidationMode(ValidationMode.ALL); // 设置执行migrate操作之前的validation行为
        flyway.setValidationErrorMode(ValidationErrorMode.FAIL); // 设置当validation失败时的系统行为

        flyway.migrate();
    }
}
