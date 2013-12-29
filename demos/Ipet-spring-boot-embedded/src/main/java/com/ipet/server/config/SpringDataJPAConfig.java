/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author yneos
 */
//@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringDataJPAConfig {

    private org.apache.tomcat.jdbc.pool.DataSource pool;

    @Bean
    public DataSource dataSource() {

        this.pool = new org.apache.tomcat.jdbc.pool.DataSource();
        //this.pool.setDriverClassName(this.getDriver());
        this.pool.setDriverClassName("com.mysql.jdbc.Driver");
        //this.pool.setUrl(this.getUrl());
        this.pool.setUrl("jdbc:mysql://localhost:3306/ipet?useUnicode=true&amp;characterEncoding=UTF-8");
        /*if (getUsername() != null) {
         this.pool.setUsername(getUsername());
         }
         if (getPassword() != null) {
         this.pool.setPassword(getPassword());
         }*/

        this.pool.setUsername("root");
        this.pool.setPassword("");
        this.pool.setMaxActive(30);
        this.pool.setMaxIdle(3);
        this.pool.setMinIdle(1);
        this.pool.setTestOnBorrow(true);
        this.pool.setTestOnReturn(true);
        this.pool.setValidationQuery("SELECT 1 from dual");
        this.pool.setJmxEnabled(true);
        return this.pool;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.ipet.server");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        factory.setJpaProperties(this.getJpaProperties());

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    private Properties getJpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "none");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
                //setProperty("hibernate.connection.characterEncoding", "UTF-8");
                //setProperty("hibernate.connection.useUnicode", "true");

                setProperty("hibernate.connection.charSet", "UTF-8");
                setProperty("hibernate.jdbc.use_streams_for_binary", "true");
                setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");

            }
        };
    }
}
