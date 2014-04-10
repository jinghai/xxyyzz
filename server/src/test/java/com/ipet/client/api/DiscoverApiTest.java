package com.ipet.client.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import com.ipet.client.api.domain.IpetPhoto;
import com.ipet.client.api.impl.IpetApiImpl;
import com.ipet.test.BaseTest;

/**
 * 
 * @author xiaojinghai
 */
public class DiscoverApiTest extends BaseTest {

	private final AccountApi accountApi = IpetApiImpl.getInstance("1", "1").getAccountApi();
	private final PhotoApi photoApi = IpetApiImpl.getInstance("1", "1").getPhotoApi();
	private final FavorApi favorApi = IpetApiImpl.getInstance("1", "1").getFavorApi();
	private final CommentApi commentApi = IpetApiImpl.getInstance("1", "1").getCommentApi();
	private final DiscoverApi discoverApi = IpetApiImpl.getInstance("1", "1").getDiscoverApi();

	@Test
	public void testDiscover() throws Exception {
		accountApi.login("admin", "admin");
		String filePath = super.getTestPhotoPath();
		FileSystemResource fsr = new FileSystemResource(filePath);
		IpetPhoto photo = photoApi.publish("测试", fsr);
		photo = photoApi.publishText(photo.getId(), "测试");
		logger.debug(photo.getText());
		Thread.sleep(1000);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateformat.format(new Date());
		logger.debug(dateStr);
		List<IpetPhoto> photos = discoverApi.listPage(dateStr, "0", "20");
		Assert.assertEquals("测试", photos.get(0).getText());

		favorApi.favor(photo.getId(), "I liked it.");
		photos = discoverApi.listPage(dateStr, "0", "20");
		Assert.assertTrue(photos.get(0).isFavored());
	}

	@Test
	public void testDiscoverWithComments() throws Exception {
		accountApi.login("admin", "admin");
		String filePath = super.getTestPhotoPath();
		FileSystemResource fsr = new FileSystemResource(filePath);
		IpetPhoto photo = photoApi.publish("测试", fsr);
		photo = photoApi.publishText(photo.getId(), "测试");
		commentApi.comment(photo.getId(), "测试评论");
		logger.debug(photo.getText());
		Thread.sleep(1000);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateformat.format(new Date());
		logger.debug(dateStr);
		List<IpetPhoto> photos = discoverApi.listPage(dateStr, "0", "20");
		Assert.assertEquals("测试", photos.get(0).getText());
		Assert.assertEquals("1", photos.get(0).getCommentCount());
		Assert.assertEquals("测试评论", photos.get(0).getComments().get(0).getText());
	}

}
