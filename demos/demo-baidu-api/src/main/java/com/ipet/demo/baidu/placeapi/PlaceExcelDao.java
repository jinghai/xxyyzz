/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.demo.baidu.placeapi;

import com.ipet.demo.baidu.placeapi.domain.Poi;
import com.ipet.demo.baidu.placeapi.domain.PoiDetail;
import com.ipet.demo.baidu.placeapi.domain.Region;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author yneos
 */
public class PlaceExcelDao {

    private static String file = "target/place.xls";

    public static void initTable(Map<Region, List<Region>> allRegions) {
        File f = new File(file);
        if (f.exists()) {
            f.delete();
        }
        String filds = "id,省,市,名称,纬度值,经度值,地址,电话,分类,标签,详情页"
                + ",商户价格,营业时间,总体评分,口味评分,服务评分,环境评分,星级评分,卫生评分,技术评分"
                + ",图片数,团购数,优惠数,评论数,收藏数,签到数";
        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        HSSFWorkbook wb = new HSSFWorkbook();

        for (Map.Entry<Region, List<Region>> entry : allRegions.entrySet()) {
            String province = entry.getKey().getName();
            // 创建Excel的工作sheet,对应到一个excel文档的tab
            HSSFSheet sheet = wb.createSheet(province);
            // 创建Excel的sheet的一行
            HSSFRow row = sheet.createRow(0);
            String[] fs = filds.split(",");
            for (int i = 0, len = fs.length; i < len; i++) {
                // 创建一个Excel的单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(fs[i]);
            }

        }

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(PlaceExcelDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void save(String province, String city, Poi p) {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(file);
            wb = new HSSFWorkbook(new POIFSFileSystem(is));
        } catch (IOException ex) {
            Logger.getLogger(PlaceExcelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        HSSFSheet sheet = wb.getSheet(province);
        HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        Float lat = null == p.getLocation() ? null : p.getLocation().getLat();
        Float lng = null == p.getLocation() ? null : p.getLocation().getLng();
        PoiDetail d = null == p.getDetail_info() ? new PoiDetail() : p.getDetail_info();
        String values = p.getUid()
                + "," + province
                + "," + city
                + "," + p.getName()
                + "," + lat
                + "," + lng
                + "," + p.getAddress()
                + "," + p.getTelephone()
                + "," + d.getType()
                + "," + d.getTag()
                + "," + d.getDetail_url()
                + "," + d.getPrice()
                + "," + d.getShop_hours()
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
                + "," + d.getCheckin_num();
        String[] v = values.split(",");
        for (int i = 0, len = v.length; i < len; i++) {
            // 创建一个Excel的单元格
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(v[i]);
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(PlaceExcelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
