/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.domain;

/**
 *
 * @author xiaojinghai
 */
public class User {

    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    //头像URL
    private String pictureUrl;
    //关注数量
    private int subscibeCount;
    //粉丝数量
    private int followerCount;
    //互粉数量
    private int friendsCount;
    //最后一次的地理位置文字说明
    private String lastLocation;
    //创建时间
    private String createAt;
    //更新时间
    private String updateAt;
}
