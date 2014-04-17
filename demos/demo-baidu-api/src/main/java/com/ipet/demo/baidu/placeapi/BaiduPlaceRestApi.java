package com.ipet.demo.baidu.placeapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipet.demo.baidu.placeapi.domain.Poi;
import com.ipet.demo.baidu.placeapi.domain.Region;
import com.ipet.demo.baidu.placeapi.domain.Result;
import java.io.IOException;
import java.net.URI;
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
public class BaiduPlaceRestApi {

    //从百度申请的AcessKey
    //管理地址：http://lbsyun.baidu.com/apiconsole/app
    public static final String ak = "559933d2044dafee4bcc08c1b691673c";
    //总请求数: 10万次/天,http://lbsyun.baidu.com/apiconsole/quota
    public static final String base_url = "http://api.map.baidu.com/place/v2/";
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public static void main(String[] args) {

        Result<Poi> res = search("宠物", "上海市", "20", "0");
        log("[上海市]共:" + res.getTotal());
        for (Poi place : res.getResults()) {
            Float lng = null == place.getLocation() ? 0f : place.getLocation().getLng();
            Float lat = null == place.getLocation() ? 0f : place.getLocation().getLat();
            log("[" + place.getName() + "]:"
                    + "电话：" + place.getTelephone()
                    + ",经度：" + lng
                    + ",纬度：" + lat
                    + ",地址：" + place.getAddress());
        }

    }

    /**
     * 城市内检索 按区域POI查询
     *
     * @param keyword 关键字
     * @param region 区域（省或市的中文名子）
     * @param page_size
     * @param page_num
     */
    public static Result<Poi> search(String keyword, String region, String page_size, String page_num) {
        //http://api.map.baidu.com/place/v2/search?ak=559933d2044dafee4bcc08c1b691673c&output=json&q=%E5%AE%A0%E7%89%A9&tag=%E5%AE%A0%E7%89%A9&region=%E4%B8%8A%E6%B5%B7%E5%B8%82&page_size=3&page_num=0
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("ak", ak);
        parameters.set("output", "json");
        parameters.set("q", keyword);
        parameters.set("tag", keyword);
        parameters.set("region", region);
        parameters.set("page_size", page_size);
        parameters.set("page_num", page_num);

        URI uri = buildUri("search", parameters);
        log(uri.toString());
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

    public static Result<Region> getChinaRegions() {
        //http://api.map.baidu.com/place/v2/search?q=a&region=%E5%85%A8%E5%9B%BD&ak=559933d2044dafee4bcc08c1b691673c&output=json
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("q", "a");
        parameters.set("region", "全国");
        parameters.set("ak", ak);
        parameters.set("output", "json");

        URI uri = buildUri("search", parameters);
        String txt = restTemplate.getForObject(uri, String.class);
        log(uri.toString());
        ObjectMapper mapper = new ObjectMapper();
        Result<Region> ret = new Result<Region>();

        //ret = restTemplate.getForObject(uri, Result.class);
        try {
            TypeReference type = new TypeReference<Result<Region>>() {
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
