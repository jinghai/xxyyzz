/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.domain;

import javax.persistence.Embeddable;

/**
 *
 * @author xiaojinghai
 */
//@Embeddable
public enum UserRole {

    //终端用户
    ENDUSER,
    //商业用户
    BUSSINESS_USER,
    //管理员
    ADMIN

}
