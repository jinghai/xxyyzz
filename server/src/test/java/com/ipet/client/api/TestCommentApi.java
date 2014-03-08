package com.ipet.client.api;

import com.ipet.client.api.domain.IpetComment;
import com.ipet.client.api.domain.IpetPhoto;
import com.ipet.client.api.impl.IpetApiImpl;
import java.io.UnsupportedEncodingException;
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
public class TestCommentApi {

    private static final Logger logger = LoggerFactory.getLogger(TestCommentApi.class);
    private final AccountApi accountApi = IpetApiImpl.getInstance("1", "1").getAccountApi();
    private final PhotoApi photoApi = IpetApiImpl.getInstance("1", "1").getPhotoApi();
    private final CommentApi commentApi = IpetApiImpl.getInstance("1", "1").getCommentApi();

    @Test
    public void comment() throws UnsupportedEncodingException {
        accountApi.login("admin", "admin");
        String filePath = java.net.URLDecoder.decode(ClassLoader.getSystemResource("四兆.JPG").getPath(), "UTF-8");
        FileSystemResource fsr = new FileSystemResource(filePath);
        IpetPhoto photo = photoApi.publish("测试", fsr);
        commentApi.comment(photo.getId(), "真好啊");
        List<IpetComment> comm = commentApi.listPage(photo.getId(), "1", "20");
        assertEquals("真好啊", comm.get(0).getText());
    }

}
