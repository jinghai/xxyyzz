package com.ipet.demo.baidu.placeapi;

import com.ipet.demo.util.RestTemplateFactory;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * 从百度申请的AcessKey,管理地址：http://lbsyun.baidu.com/apiconsole/app
 * 总请求数10万次/天,http://lbsyun.baidu.com/apiconsole/quota
 */
public class BaiduPlaceApi {

    private static final String ak = "559933d2044dafee4bcc08c1b691673c";

    private static final String base_url = "http://api.map.baidu.com/place/v2/";

    private static final RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();

    //以下地址在placeApi中出现在省级位置，却可以按市级直接查询
    private static final ArrayList<String> sCity = new ArrayList<String>() {
        {
            add("北京市");
            add("上海市");
            add("广州市");
            add("深圳市");
            add("天津市");
            add("南京市");
            add("重庆市");
            add("成都市");
            add("澳门特别行政区");
            add("香港特别行政区");
        }
    };
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    //按区域查询Poi
    public static Result<Poi> searchByRegion(String region, String keyword) {
        Integer pageNum = 0;
        Result<Poi> result = searchPoiForResult(region, keyword, pageNum.toString());
        if (result.getResults().size() == 20) {
            while (true) {
                pageNum++;
                Result<Poi> ret = searchPoiForResult(region, keyword, pageNum.toString());
                int currCount = result.getResults().size() + ret.getResults().size();
                //Main.log("currCount:" + currCount + ",total:" + ret.getTotal() + ",pageNum:" + pageNum + ",return:" + ret.getResults().size());
                if (currCount < ret.getTotal() && pageNum < 38 && ret.getResults().size() < 20) {
                    int retryCount = 0;
                    while (true) {
                        retryCount++;
                        try {
                            Thread.sleep(retryCount * 1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(BaiduPlaceApi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Main.log("retry:" + retryCount);
                        ret = searchPoiForResult(region, keyword, pageNum.toString());
                        if (ret.getResults().size() == 20 || retryCount < 3) {
                            break;
                        }
                    }
                }
                result.getResults().addAll(ret.getResults());
                //Main.log("curr size:" + ret.getResults().size());
                if (ret.getResults().size() < 20) {
                    break;
                }

            }
        }
        return result;
    }

    //获取全国的区域（省，市）
    public static Map<Region, List<Region>> getRegions(String key) {

        Map<Region, List<Region>> allRegion = new HashMap<Region, List<Region>>();

        //所有省和区
        List<Region> regions = getRegionsForList("全国", key);
        //初始化省数据
        for (Region r : regions) {

            if (sCity.contains(r.getName())) {
                //如果是直辖市则其管辖范围为本市
                List<Region> city = new ArrayList<Region>();
                city.add(r);
                //排除掉“"成都市"”这条错误的省级记录
                if (!"成都市".equals(r.getName())) {
                    allRegion.put(r, city);
                }

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

    //分页查询Region
    public static List<Region> getRegionsForList(String region, String key) {
        Result<Region> ret = getRegionsForResult(region, key);
        return ret.getResults();
    }

    //分页查询Region
    public static Result<Region> getRegionsForResult(String region, String keyword) {
        String json = request(region, keyword, "0");
        Result<Region> result = json2Region(json);
        return result;
    }

    //分页查询poi
    public static List<Poi> searchPoiForList(String region, String keyword, String page_num) {
        Result<Poi> ret = searchPoiForResult(region, keyword, page_num);
        return ret.getResults();
    }

    //分页查询poi
    public static Result<Poi> searchPoiForResult(String region, String keyword, String page_num) {
        String json = request(region, keyword, page_num);
        Result<Poi> poi = json2Poi(json);
        //Main.log("状态码：" + poi.getStatus());
        return poi;
    }

    //api请求
    public static String request(String region, String keyword, String page_num) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("ak", ak);
        parameters.set("output", "json");
        parameters.set("page_size", "20");
        parameters.set("scope", "2");
        parameters.set("q", keyword);
        parameters.set("tag", keyword);
        parameters.set("region", region);
        parameters.set("page_num", page_num);

        URI uri = buildUri("search", parameters);
        //Main.log(uri.toString());
        String txt = restTemplate.getForObject(uri, String.class);
        //Main.log(txt);
        return txt;
    }

    //json转Region
    public static Result<Region> json2Region(String jsonStr) {
        Result<Region> ret = new Result<Region>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference type = new TypeReference<Result<Region>>() {
        };
        try {
            ret = mapper.readValue(jsonStr, type);
        } catch (IOException ex) {
            Logger.getLogger(BaiduPlaceApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    //json转poi
    public static Result<Poi> json2Poi(String jsonStr) {
        Result<Poi> ret = new Result<Poi>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference type = new TypeReference<Result<Poi>>() {
        };
        try {
            ret = mapper.readValue(jsonStr, type);
        } catch (IOException ex) {
            Logger.getLogger(BaiduPlaceApi.class.getName()).log(Level.SEVERE, null, ex);
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

}
