package com.ipet.demo.baidu.placeapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipet.demo.baidu.placeapi.domain.Region;
import com.ipet.demo.baidu.placeapi.domain.Result;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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
        getChinaRegions();
    }

    /**
     * 按区域POI查询
     *
     * @param keyword 关键字
     * @param region 区域（省或市的中文名子）
     * @param page_size
     * @param page_num
     */
    public static void search(String keyword, String region, String page_size, String page_num) {

    }

    public static void getPOIDetail() {
        //todo
    }

    public static void getChinaRegions() {

        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("q", "a");
        parameters.set("region", "全国");
        parameters.set("ak", ak);
        parameters.set("output", "json");

        //todo
        URI uri = buildUri("search", parameters);
        log(uri.toString());
        String txt = restTemplate.getForObject(uri, String.class);
        log(txt);
        ObjectMapper mapper = new ObjectMapper();
        Result<Region> ret = new Result<Region>();

        try {
            //ret = mapper.readValue(txt, Result.class);
            ret = mapper.readValue(txt, new TypeReference<Result<Region>>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log(ret.getResults().get(1).getName());

        //Result<Region> ret = restTemplate.getForObject(uri, Result.class);
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
