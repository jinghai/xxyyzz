/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 区域
 *
 * @author xiaojinghai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {

    private String name;    //区域名称/省
    private Integer num;    //数量

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }

}
