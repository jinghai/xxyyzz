package com.ipet.client.api;

import com.ipet.client.api.domain.IpetFavor;
import com.ipet.client.api.domain.IpetPhoto;
import com.ipet.client.api.impl.IpetApiImpl;
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
public class TestFavorApi {

    private static final Logger logger = LoggerFactory.getLogger(TestFavorApi.class);
    private final AccountApi accountApi = IpetApiImpl.getInstance("1", "1").getAccountApi();
    private final PhotoApi photoApi = IpetApiImpl.getInstance("1", "1").getPhotoApi();
    private final FavorApi favorApi = IpetApiImpl.getInstance("1", "1").getFavorApi();

    @Test()
    public void favor() {
        accountApi.login("admin", "admin");
        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("4M.JPG").getPath());
        IpetPhoto photo = photoApi.publish("测试", fsr);
        favorApi.favor(photo.getId(), "喜欢");
        List<IpetFavor> comm = favorApi.list(photo.getId());
        assertEquals("喜欢", comm.get(0).getText());
    }

}
