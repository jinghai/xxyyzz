/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi.domain;

/**
 *
 * @author xiaojinghai
 */
public class Poi {

    private String name;            //poi名称
    private Location location;      //位置
    private String address;         //poi地址信息
    private String telephone;       //poi电话信息
    private String uid;             //poi的唯一标示
    private PoiDetail detail_info;  //poi的扩展信息，仅当scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。

}
