/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ipet.server.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
@EnableTransactionManagement
@ConfigurationProperties(name = "db.jdbc", ignoreUnknownFields = false)
@EnableConfigurationProperties
@EnableJpaRepositories
public class DataSourceConfig {

    private String driver;
    private String url;
    private String username;
    private String password;
    private String mode;

    private org.apache.tomcat.jdbc.pool.DataSource pool;

    @Bean
    public DataSource dataSource() {
        if (null != mode) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder.setType(EmbeddedDatabaseType.HSQL).build();
        } else {
            this.pool = new org.apache.tomcat.jdbc.pool.DataSource();
            this.pool.setDriverClassName(this.getDriver());
            //this.pool.setDriverClassName("com.mysql.jdbc.Driver");
            this.pool.setUrl(this.getUrl());
            //this.pool.setUrl("jdbc:mysql://localhost:3306/ipet?useUnicode=true&amp;characterEncoding=UTF-8");
            if (getUsername() != null) {
                this.pool.setUsername(getUsername());
            }
            if (getPassword() != null) {
                this.pool.setPassword(getPassword());
            }

            //this.pool.setUsername("root");
            //this.pool.setPassword("");
            this.pool.setMaxActive(30);
            this.pool.setMaxIdle(3);
            this.pool.setMinIdle(1);
            this.pool.setTestOnBorrow(true);
            this.pool.setTestOnReturn(true);
            this.pool.setValidationQuery("SELECT 1 from dual");
            this.pool.setJmxEnabled(true);
            return this.pool;
        }

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        //vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getClass().getPackage().getName());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(getJpaProperties());

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
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

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

}
