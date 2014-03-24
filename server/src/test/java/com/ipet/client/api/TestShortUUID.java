package com.ipet.client.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipet.client.api.impl.IpetApiImpl;
import com.ipet.server.util.ProjectUtil;

/**
 * 
 * @author xiaojinghai
 */
public class TestShortUUID {

	private static final Logger logger = LoggerFactory.getLogger(TestShortUUID.class);
	private AccountApi api = IpetApiImpl.getInstance("1", "1").getAccountApi();

	// 测试短UUID重复的可能性
	@Test
	public void testShortUUID() {
		// long times = 100 * 10000; //一百万次
		long times = 10;
		long start = System.currentTimeMillis();
		for (long i = 0; i < times; i++) {
			api.register(ProjectUtil.generateShortUUID(), "test");
		}
		long end = System.currentTimeMillis();
		Long useAV = (end - start) / times;
		logger.debug("useAV:" + useAV);
	}

}
