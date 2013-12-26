/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

/**
 *
 * @author xiaojinghai
 */
public enum UserRole {

    END("终端用户", "end"), BUS("商户", "bus"), ADM("管理员", "adm");
    // 成员变量
    private final String name;
    private final String value;

    // 构造方法
    private UserRole(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

}
