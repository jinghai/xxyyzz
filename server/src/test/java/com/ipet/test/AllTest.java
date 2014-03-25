package com.ipet.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ipet.client.api.AppApiTest;
import com.ipet.client.api.FeedbackApiTest;
import com.ipet.client.api.TestAccountApi;
import com.ipet.client.api.TestCommentApi;
import com.ipet.client.api.TestContactApi;
import com.ipet.client.api.TestDescoverApi;
import com.ipet.client.api.TestFavorApi;
import com.ipet.client.api.TestPhotoApi;
import com.ipet.client.api.TestShortUUID;
import com.ipet.client.api.TestShortUUIDForMultiThreaded;
import com.ipet.client.api.TestUserApi;
import com.ipet.jetty.JettyServer;
import com.ipet.server.web.rest.base.TestFilePath;
import com.ipet.server.web.rest.v1.TestAccountRestController;
import com.ipet.server.web.rest.v1.TestUserRestController;
import com.ipet.server.web.rest.v1.UpdateAppTool;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AppApiTest.class, FeedbackApiTest.class, TestAccountApi.class, TestCommentApi.class,
		TestContactApi.class, TestDescoverApi.class, TestFavorApi.class, TestPhotoApi.class, TestShortUUID.class,
		TestShortUUIDForMultiThreaded.class, TestUserApi.class, TestFilePath.class, TestAccountRestController.class,
		TestUserRestController.class, UpdateAppTool.class })
public class AllTest {

	@BeforeClass
	public static void setUp() throws Exception {
		JettyServer.start();
	}

	@AfterClass
	public static void teardown() throws Exception {
		JettyServer.stop();
	}

}
