package com.ipet.db.util;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yneos
 */
enum Dialect {

    MYSQL("org.hibernate.dialect.MySQL5InnoDBDialect"),
    ORACLE("org.unhcr.omss.db.oracle.OracleDialectDeferredFK"),
    SYBASE("org.hibernate.dialect.SybaseAnywhereDialect");

    private String className;

    private Dialect(String className) {

        this.className = className;

    }

    public String getClassName() {

        return className;

    }

}
