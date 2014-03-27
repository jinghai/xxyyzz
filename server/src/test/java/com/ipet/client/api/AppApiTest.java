package com.ipet.client.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipet.client.api.domain.IpetAppUpdate;
import com.ipet.client.api.impl.IpetApiImpl;

public class AppApiTest {

	private static final Logger logger = LoggerFactory.getLogger(AppApiTest.class);
	private final AppApi apApi = IpetApiImpl.getInstance("ipet", "ipet").getAppApi();

	@Test
	public void testCheckAppVersion() {
		IpetAppUpdate ret = apApi.checkAppVersion("ipet");
		logger.debug(ToStringBuilder.reflectionToString(ret));
	}

}
