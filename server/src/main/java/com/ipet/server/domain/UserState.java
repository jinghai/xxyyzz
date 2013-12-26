/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

/**
 *
 * @author xiaojinghai
 */
public enum UserState {

    ENABLE("启用", 1), DISABLE("停用", 2), VERIFYING("待验证", 3);
    // 成员变量
    private final String name;
    private final int index;

    // 构造方法
    private UserState(String name, int index) {
        this.name = name;
        this.index = index;
    }

}
