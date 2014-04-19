/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi;

import com.ipet.demo.baidu.placeapi.domain.Poi;
import com.ipet.demo.baidu.placeapi.domain.PoiDetail;
import com.ipet.demo.util.H2Util;

/**
 *
 * @author yneos
 */
public class PlaceH2Dao {

    public static final String tableName = "poi";
    public static Integer count = 0;
    public static final String filds = "id,省,市,名称,纬度值,经度值,地址,电话,分类,标签,详情页"
            + ",商户价格,营业时间,总体评分,口味评分,服务评分,环境评分,星级评分,卫生评分,技术评分"
            + ",图片数,团购数,优惠数,评论数,收藏数,签到数";

    public static void initTable() {
        String drop = "drop table if exists " + tableName;
        String sql = "create table if not exists " + tableName
                + " ("
                + "id VARCHAR "
                //+ "id VARCHAR PRIMARY KEY"
                + ",省 VARCHAR"
                + ",市 VARCHAR"
                + ",名称 VARCHAR"
                + ",纬度值 FLOAT"
                + ",经度值 FLOAT"
                + ",地址 VARCHAR"
                + ",电话 VARCHAR"
                + ",分类 VARCHAR"
                + ",标签 VARCHAR"
                + ",详情页 VARCHAR"
                + ",商户价格 FLOAT"
                + ",营业时间 VARCHAR"
                + ",总体评分 FLOAT"
                + ",口味评分 FLOAT"
                + ",服务评分 FLOAT"
                + ",环境评分 FLOAT"
                + ",星级评分 FLOAT"
                + ",卫生评分 FLOAT"
                + ",技术评分 FLOAT"
                + ",图片数 INTEGER"
                + ",团购数 INTEGER"
                + ",优惠数 INTEGER"
                + ",评论数 INTEGER"
                + ",收藏数 INTEGER"
                + ",签到数 INTEGER"
                + ")";
        //H2Util.executeUpdate(drop);
        H2Util.executeUpdate(sql);
    }

    public static void save(String province, String city, Poi p) {
        count++;

        Float lat = null == p.getLocation() ? null : p.getLocation().getLat();
        Float lng = null == p.getLocation() ? null : p.getLocation().getLng();
        PoiDetail d = null == p.getDetail_info() ? new PoiDetail() : p.getDetail_info();
        String values = "'" + stringValue(p.getUid()) + "'"
                + ",'" + stringValue(province) + "'"
                + ",'" + stringValue(city) + "'"
                + ",'" + stringValue(p.getName()) + "'"
                + "," + lat
                + "," + lng
                + ",'" + stringValue(p.getAddress()) + "'"
                + ",'" + stringValue(p.getTelephone()) + "'"
                + ",'" + stringValue(d.getType()) + "'"
                + ",'" + stringValue(d.getTag()) + "'"
                + ",'" + stringValue(d.getDetail_url()) + "'"
                + "," + d.getPrice()
                + ",'" + stringValue(d.getShop_hours()) + "'"
                + "," + d.getOverall_rating()
                + "," + d.getTaste_rating()
                + "," + d.getService_rating()
                + "," + d.getEnvironment_rating()
                + "," + d.getFacility_rating()
                + "," + d.getHygiene_rating()
                + "," + d.getTechnology_rating()
                + "," + d.getImage_num()
                + "," + d.getGroupon_num()
                + "," + d.getDiscount_num()
                + "," + d.getComment_num()
                + "," + d.getFavorite_num()
                + "," + d.getCheckin_num()
                + "";
        String sql = "INSERT INTO " + tableName + " (" + filds + ") VALUES (" + values + ")";
        H2Util.executeUpdate(sql);

    }

    public static String stringValue(String str) {
        String ret = str == null ? "" : str.replace("'", "\'");
        return ret;
    }

    public static Float floatValue(Float f) {
        Float ret = f == null ? null : f;
        return ret;
    }

    public static void main(String[] arge) {
        initTable();
        Poi p = new Poi();
        save("", "", p);
    }
}
