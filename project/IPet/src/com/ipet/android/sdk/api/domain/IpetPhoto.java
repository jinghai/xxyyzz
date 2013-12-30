/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk.api.domain;

import java.io.File;

/**
 *
 * @author xiaojinghai
 */
public class IpetPhoto {

    private Long id;
    //缩略图地址
    private String smallUrl;
    //原始图片地址
    private String url;
    //文本描述
    private String text;
    //被转发数量
    private int forwordCount;
    //被赞数量
    private int favorCount;
    //被评论数量
    private int commentCount;
    //经度
    private Float latitude;
    //纬度
    private Float longitude;
    //发布时的地理位置文字说明
    private String location;
    //创建时间
    private String createAt;
    //最后评论时间
    private String updateAt;

    //图片文件，发布图片时使用
    private File photoFile;
}
