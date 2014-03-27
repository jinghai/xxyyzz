package com.ipet.server.web.rest.v1;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ipet.server.domain.entity.User;
import com.ipet.server.web.rest.base.BaseTest;

/**
 * 
 * @author xiaojinghai
 */
public class UpdateAppTool extends BaseTest {

	public static final String baseUrl = "http://localhost:8080/server/api/v1/app";

	// public static final String baseUrl =
	// "http://jinghai.imblog.in:8080/server/api/v1/app";

	@Test
	public void update() {
		// 发送MultiValueMap参数
		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.add("appKey", "ipet");
		request.add("versionCode", "3");
		request.add("versionName", "0.3");
		request.add("downloadUrl", "http://jinghai.imblog.in:8080/server/files/update/android/IPet-release.apk");
		User r = restTemplate.postForObject(baseUrl + "/update", request, User.class);
	}

}
