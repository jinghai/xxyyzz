/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.db;

import java.util.Date;

/**
 *
 * @author xiaojinghai
 */
public class User {

    public Integer id;

    public String account;

    public String name;

    public String password;

    public User(Integer id, String account, String name, String password) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
    }

}
