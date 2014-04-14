package com.ipet.server.web.rest.v1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ipet.jetty.JettyServer;
import com.ipet.server.domain.entity.App;
import com.ipet.server.web.rest.base.BaseTest;

/**
 *
 * @author xiaojinghai
 */
public class UpdateAppTool extends BaseTest {

    public static final String baseUrl = "http://localhost:8080/server/api/v1/app";
    //public static final String baseUrl = "http://api.ipetty.net/api/v1/app";

    @Before
    public void setUp() throws Exception {
        JettyServer.start();
    }

    @After
    public void tearDown() throws Exception {
        JettyServer.stop();
    }

    @Test
    public void update() {
        // 发送MultiValueMap参数
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("appKey", "ipet");
        request.add("versionCode", "6");
        request.add("versionName", "0.6");
        request.add("downloadUrl", "http://api.ipetty.net/files/update/android/IPet-release.apk");
        request.add("text", "1.xxx\r\n2.yyy\r\n3.zzz");

        restTemplate.postForObject(baseUrl + "/update", request, App.class);
    }

}
