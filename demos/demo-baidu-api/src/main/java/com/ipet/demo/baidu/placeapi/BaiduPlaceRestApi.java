package com.ipet.demo.baidu.placeapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
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
    public static final String base_url = "http://api.map.baidu.com/place/v2/";
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public static void main(String[] args) {

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
        //这里只演示一个最简单查询，详细使用方法请见：http://developer.baidu.com/map/webservice-placeapi.htm
        RestApi rest = new RestApi(base_url);
        //todo
    }

    public static void getPOIDetail() {
        //todo
    }

    public static void getChinaRegions() {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("q", "a");
        parameters.set("region", "全国");
        parameters.set("ak", ak);
        //todo
        URI uri = buildUri("discover/listPage", parameters);
        restTemplate.getForObject(uri, Object.class);

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
