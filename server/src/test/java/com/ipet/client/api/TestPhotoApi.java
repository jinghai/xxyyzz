package com.ipet.client.api;

import com.ipet.client.api.domain.IpetPhoto;
import com.ipet.client.api.impl.IpetApiImpl;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
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
public class TestPhotoApi {

    private static final Logger logger = LoggerFactory.getLogger(TestPhotoApi.class);
    private final PhotoApi api = IpetApiImpl.getInstance("1", "1").getPhotoApi();

    public void publishPhoto() {
        //IpetApiImpl.getInstance("1", "1").getAccountApi().login("admin", "admin");
        String fs = ClassLoader.getSystemResource("4M.JPG").getPath();
        File f = new File(ClassLoader.getSystemResource("4M.JPG").getPath());
        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("4M.JPG").getPath());
        IpetPhoto ret = api.publish("测试", fsr);
        logger.debug(ToStringBuilder.reflectionToString(ret));
    }

    @Test
    public void listFollow() throws InterruptedException {
        IpetApiImpl.getInstance("1", "1").getAccountApi().login("admin", "admin");
        for (int i = 0; i < 9; i++) {
            publishPhoto();
        }
        Thread.sleep(1000);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(new Date());
        List<IpetPhoto> ret = api.listFollowd(dateStr, "1", "4");
        assertEquals(4, ret.size());
        ret = api.listFollowd(dateStr, "2", "4");
        assertEquals(4, ret.size());
    }

}
