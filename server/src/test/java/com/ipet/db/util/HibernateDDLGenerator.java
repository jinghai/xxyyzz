package com.ipet.db.util;

import com.ipet.server.domain.entity.App;
import com.ipet.server.domain.entity.Comment;
import com.ipet.server.domain.entity.Favor;
import com.ipet.server.domain.entity.FollowRelation;
import com.ipet.server.domain.entity.FriendRelation;
import com.ipet.server.domain.entity.LastLocation;
import com.ipet.server.domain.entity.Photo;
import com.ipet.server.domain.entity.Shop;
import com.ipet.server.domain.entity.User;
import com.ipet.server.domain.entity.UserProfile;
import com.ipet.server.domain.entity.UserSetting;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * DDL生成,需先构建项目再执行
 *
 * @author yneos
 */
public class HibernateDDLGenerator {

    public static void main(String[] args) {
        new HibernateDDLGenerator().execute(Dialect.MYSQL,
                App.class,
                Comment.class,
                Favor.class,
                FollowRelation.class,
                FriendRelation.class,
                LastLocation.class,
                Photo.class,
                Shop.class,
                User.class,
                UserProfile.class,
                UserSetting.class
        );

    }

    private void execute(Dialect dialect, Class<?>... classes) {

        AnnotationConfiguration configuration = new AnnotationConfiguration();

        configuration.setProperty(Environment.DIALECT, dialect.getClassName());
        for (Class<?> entityClass : classes) {

            configuration.addAnnotatedClass(entityClass);
        }

        SchemaExport schemaExport = new SchemaExport(configuration);

        schemaExport.setDelimiter(";");

        schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[]{"ddl", dialect.name().toLowerCase(), "sql"}));

        boolean consolePrint = true;

        boolean exportInDatabase = false;

        schemaExport.create(consolePrint, exportInDatabase);

    }
}
