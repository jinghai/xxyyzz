package com.ipet.client.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipet.client.api.impl.IpetApiImpl;

public class FeedbackApiTest {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackApiTest.class);
	private final AccountApi accountApi = IpetApiImpl.getInstance("1", "1").getAccountApi();
	private final FeedbackApi feedbackApi = IpetApiImpl.getInstance("1", "1").getFeedbackApi();

	// @Test
	// public void testFeedback() {
	// accountApi.login("admin", "admin");
	// }

	@Test
	public void testFeedbackAnonymous() {
		feedbackApi.feedback("", "测试意见反馈", "");
	}

}
