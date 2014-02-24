package com.ipet.client.api;

import com.ipet.client.api.domain.IpetPhoto;
import com.ipet.client.api.impl.IpetApiImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class TestDescoverApi {

    private static final Logger logger = LoggerFactory.getLogger(TestDescoverApi.class);
    private final AccountApi accountApi = IpetApiImpl.getInstance("1", "1").getAccountApi();
    private final PhotoApi photoApi = IpetApiImpl.getInstance("1", "1").getPhotoApi();
    private final DiscoverApi discoverApi = IpetApiImpl.getInstance("1", "1").getDiscoverApi();

    @Test
    public void comment() throws InterruptedException {
        accountApi.login("admin", "admin");
        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("4M.JPG").getPath());
        IpetPhoto photo = photoApi.publish("测试", fsr);
        logger.debug(photo.getText());
        Thread.sleep(1000);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(new Date());
        logger.debug(dateStr);
        List<IpetPhoto> list = discoverApi.listPage(dateStr, "1", "20");
        assertEquals("测试", list.get(0).getText());
    }

}
