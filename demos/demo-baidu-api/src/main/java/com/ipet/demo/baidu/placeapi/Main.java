package com.ipet.demo.baidu.placeapi;

import com.ipet.demo.baidu.placeapi.domain.Poi;
import com.ipet.demo.baidu.placeapi.domain.Region;
import com.ipet.demo.baidu.placeapi.domain.Result;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author xiaojinghai
 *
 * API文档：http://developer.baidu.com/map/webservice-placeapi.htm
 * 从百度申请的AcessKey,管理地址：http://lbsyun.baidu.com/apiconsole/app
 * 总请求数10万次/天,http://lbsyun.baidu.com/apiconsole/quota
 */
public class Main {

    public static void main(String[] args) {

        String keyword = "宠物";

        Map<Region, List<Region>> allRegions = BaiduPlaceApi.getRegions(keyword);
        PlaceExcelDao.initTable(allRegions);
        PlaceSqliteDao.initTable();
        PlaceH2Dao.initTable();
        log("共有:" + allRegions.size() + "个省，总计");
        for (Map.Entry<Region, List<Region>> entry : allRegions.entrySet()) {
            Region province = entry.getKey();
            log(province.getName() + ",全地区共有：" + province.getNum());

        }
        //详细
        for (Map.Entry<Region, List<Region>> entry : allRegions.entrySet()) {
            List<Region> citys = entry.getValue();
            String province = entry.getKey().getName();
            log("正在获取" + province + ",省级数据");
            for (Region city : citys) {
                String cityName = city.getName();
                Result<Poi> result = BaiduPlaceApi.searchByRegion(cityName, keyword);
                log(cityName + "应有:" + result.getTotal());
                log(cityName + "实有:" + result.getResults().size() + "\n");
                for (Poi p : result.getResults()) {
                    PlaceSqliteDao.save(province, cityName, p);
                    PlaceH2Dao.save(province, cityName, p);
                    PlaceExcelDao.save(province, cityName, p);
                }
            }
        }

    }

    public static void log(Object o) {
        if (o instanceof String) {
            System.out.println(o);
        } else {
            System.out.println(ToStringBuilder.reflectionToString(o));
        }

    }
}
