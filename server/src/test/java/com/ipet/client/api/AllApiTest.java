package com.ipet.client.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ipet.jetty.JettyServer;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AppApiTest.class, FeedbackApiTest.class, TestAccountApi.class, TestCommentApi.class,
		TestContactApi.class, TestDescoverApi.class, TestFavorApi.class, TestPhotoApi.class, TestShortUUID.class,
		TestShortUUIDForMultiThreaded.class, TestUserApi.class })
public class AllApiTest {

	@BeforeClass
	public static void setUp() throws Exception {
		JettyServer.start();
	}

	@AfterClass
	public static void teardown() throws Exception {
		JettyServer.stop();
	}

	@Test
	public void test() {
		System.out.println("ok");
	}

}
