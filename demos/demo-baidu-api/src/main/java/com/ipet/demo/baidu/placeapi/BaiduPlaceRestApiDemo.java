package com.ipet.demo.baidu.placeapi;

import com.ipet.demo.baidu.placeapi.tool.RestTemplateFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipet.demo.baidu.placeapi.domain.Poi;
import com.ipet.demo.baidu.placeapi.domain.Region;
import com.ipet.demo.baidu.placeapi.domain.Result;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 *
 * API文档：http://developer.baidu.com/map/webservice-placeapi.htm
 */
public class BaiduPlaceRestApiDemo {

    //从百度申请的AcessKey,管理地址：http://lbsyun.baidu.com/apiconsole/app
    public static final String ak = "559933d2044dafee4bcc08c1b691673c";

    //总请求数: 10万次/天,http://lbsyun.baidu.com/apiconsole/quota
    public static final String base_url = "http://api.map.baidu.com/place/v2/";

    //直辖市和特殊区域，baiduApi会对以下几个城市直接返回查询结果，不需要进一步查询下级市
    public static final ArrayList<String> sCity = new ArrayList<String>() {
        {
            add("北京市");
            add("上海市");
            add("广州市");
            add("深圳市");
            add("成都市");
            add("澳门特别行政区");
            add("香港特别行政区");
        }
    };
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public static void main(String[] args) {
        String keyword = "宠物";
        //统计
        Map<Region, List<Region>> total = getTotalCount(keyword);
        log("----统计信息----");
        for (Map.Entry<Region, List<Region>> entry : total.entrySet()) {
            Region key = entry.getKey();
            List<Region> citys = entry.getValue();
            log("[" + key.getName() + "]" + "地区:" + key.getNum());
            for (Region r : citys) {
                log("  " + r.getName() + ":" + r.getNum());
            }
        }

        log("----详细信息----");
        //详细
        Map<String, List<Region>> allRegions = getChinaRegions(keyword);
        for (Map.Entry<String, List<Region>> entry : allRegions.entrySet()) {
            List<Region> citys = entry.getValue();
            String key = entry.getKey();
            for (Region r : citys) {
                log("[" + key + " " + r.getName() + "]:" + r.getNum());
                List<Poi> pois = searchPoiForList(r.getName(), keyword, "0");
                for (Poi p : pois) {
                    Float lng = null == p.getLocation() ? 0f : p.getLocation().getLng();
                    Float lat = null == p.getLocation() ? 0f : p.getLocation().getLat();
                    log("  " + p.getName() + ","
                            + "电话：" + p.getTelephone()
                            + ",经度：" + lng
                            + ",纬度：" + lat
                            + ",地址：" + p.getAddress());
                }
            }
        }

    }

    //获取统计信息
    private static Map<Region, List<Region>> getTotalCount(String key) {

        Map<Region, List<Region>> allRegion = new HashMap<Region, List<Region>>();

        //所有省和区
        List<Region> regions = getRegionsForList("全国", key);
        //初始化省数据
        for (Region r : regions) {
            if (sCity.contains(r.getName())) {
                //如果是直辖市则其管辖范围为本市
                List<Region> city = new ArrayList<Region>();
                city.add(r);
                allRegion.put(r, city);
            } else {
                List<Region> city = new ArrayList<Region>();
                allRegion.put(r, city);
            }
        }
        //查询填充省所管辖的城市
        for (Map.Entry<Region, List<Region>> entry : allRegion.entrySet()) {
            Region province = entry.getKey();
            List<Region> citys = entry.getValue();
            //细化非直辖市,直辖市不需要再细化
            if (!sCity.contains(province.getName())) {
                List<Region> ret = getRegionsForList(province.getName(), key);
                citys.addAll(ret);
            }

        }
        return allRegion;
    }

    //获取省市Map<"省",List<"市">>
    private static Map<String, List<Region>> getChinaRegions(String keyword) {

        Map<String, List<Region>> allRegion = new HashMap<String, List<Region>>();

        //所有省和区
        List<Region> regions = getRegionsForList("全国", keyword);
        //初始化省数据
        for (Region r : regions) {
            if (sCity.contains(r.getName())) {
                //如果是直辖市则其管辖范围为本市
                List<Region> city = new ArrayList<Region>();
                city.add(r);
                allRegion.put(r.getName(), city);
            } else {
                List<Region> city = new ArrayList<Region>();
                allRegion.put(r.getName(), city);
            }
        }
        //查询填充省所管辖的城市
        for (Map.Entry<String, List<Region>> entry : allRegion.entrySet()) {
            String key = entry.getKey();
            List<Region> citys = entry.getValue();
            //细化非直辖市,直辖市不需要再细化
            if (!sCity.contains(key)) {
                List<Region> ret = getRegionsForList(key, keyword);
                citys.addAll(ret);
            }

        }
        return allRegion;
    }

    private static List<Region> getRegionsForList(String region, String key) {
        Result<Region> ret = getRegionsForResult(region, key);
        return ret.getResults();
    }

    private static Result<Region> getRegionsForResult(String region, String key) {
        //http://api.map.baidu.com/place/v2/search?q=a&region=%E5%85%A8%E5%9B%BD&ak=559933d2044dafee4bcc08c1b691673c&output=json
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("q", key);
        parameters.set("region", region);
        parameters.set("ak", ak);
        parameters.set("output", "json");

        URI uri = buildUri("search", parameters);
        String txt = restTemplate.getForObject(uri, String.class);
        //log(uri.toString());
        ObjectMapper mapper = new ObjectMapper();
        Result<Region> ret = new Result<Region>();
        //ret = restTemplate.getForObject(uri, Result.class);//这样解析JSON无法获取泛型对象
        try {
            //JSON转换成泛型对象
            TypeReference type = new TypeReference<Result<Region>>() {
            };
            ret = mapper.readValue(txt, type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 城市内检索 按区域POI查询
     *
     * @param keyword 关键字
     * @param region 区域（省或市的中文名子）
     * @param page_size
     * @param page_num
     * @return 每次最多返回20条记录
     */
    private static List<Poi> searchPoiForList(String region, String keyword, String page_num) {
        Result<Poi> ret = searchPoiForResult(region, keyword, page_num);
        return ret.getResults();
    }

    private static Result<Poi> searchPoiForResult(String region, String keyword, String page_num) {
        //http://api.map.baidu.com/place/v2/search?ak=559933d2044dafee4bcc08c1b691673c&output=json&q=%E5%AE%A0%E7%89%A9&tag=%E5%AE%A0%E7%89%A9&region=%E4%B8%8A%E6%B5%B7%E5%B8%82&page_size=3&page_num=0
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("ak", ak);
        parameters.set("output", "json");
        parameters.set("page_size", "20");
        parameters.set("q", keyword);
        parameters.set("tag", keyword);
        parameters.set("region", region);
        parameters.set("page_num", page_num);

        URI uri = buildUri("search", parameters);
        //log(uri.toString());
        String txt = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Result<Poi> ret = new Result<Poi>();

        try {
            TypeReference type = new TypeReference<Result<Poi>>() {
            };
            ret = mapper.readValue(txt, type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    public static URI buildUri(String path, String parameterName, String parameterValue) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(parameterName, parameterValue);
        return buildUri(path, parameters);
    }

    public static URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(base_url + path).queryParams(parameters).build();
    }

    public static void log(Object o) {
        if (o instanceof String) {
            System.out.println(o);
        } else {
            System.out.println(ToStringBuilder.reflectionToString(o));
        }

    }
}
