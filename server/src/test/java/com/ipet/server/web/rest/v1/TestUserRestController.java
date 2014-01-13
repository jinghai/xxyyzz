package com.ipet.server.web.rest.v1;

import com.ipet.server.web.rest.base.MyErrorHandler;
import com.ipet.server.web.rest.base.BaseTest;
import com.ipet.server.domain.entity.User;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class TestUserRestController extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TestUserRestController.class);

    public static final String baseUrl = "http://localhost:8084/server/api/v1/user";

    @Test
    public void getOneUser() {
        //使用URI模板方式,参数也可使用Map<String, Object> urlVariables = new HashMap< String, Object>();
        User user = restTemplate.getForObject(baseUrl + "/{id}", User.class, "1");
        logger.info(user.toString());
    }

    @Test
    public void getUsers() {
        //MultiValueMap<String, List<Long>> body = new LinkedMultiValueMap<String, List<Long>>();
        //Map<String, Object> urlVariables = new HashMap< String, Object>();
        //URL参数需要构建在URL字符串中
        User[] users = restTemplate.getForObject(baseUrl + "/listByIds?ids=1,2", User[].class);
        List<User> list = Arrays.asList(users);
        logger.info(list.toString());
    }

    @Test
    public void getUsersForOne() {
        //MultiValueMap<String, List<Long>> body = new LinkedMultiValueMap<String, List<Long>>();
        //Map<String, Object> urlVariables = new HashMap< String, Object>();
        //URL参数需要构建在URL字符串中
        User[] users = restTemplate.getForObject(baseUrl + "/listByIds?ids=1", User[].class);
        List<User> list = Arrays.asList(users);
        logger.info(list.toString());
    }

    @Test
    public void update() {
        //直接发送实体，自动转为Json格式提交到服务器
        User u = new User();
        u.setId(1l);
        u.setEmail("test@test.com");
        u.setPhone("111111111");
        u = restTemplate.postForObject(baseUrl + "/update", u, User.class);
        logger.info(u.toString());
    }

    @Test
    public void uploadForHttpEntity() {
        MyErrorHandler errorHandler = new MyErrorHandler();
        restTemplate.setErrorHandler(errorHandler);

        String url = baseUrl + "/upload";

        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("4M.JPG").getPath());

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userId", "1");
        body.add("file", fsr);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(body, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        logger.info(response.toString());
        //restTemplate.postForObject(url, body, String.class);
    }

    @Test
    public void uploadFor4M() {
        MyErrorHandler errorHandler = new MyErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        logger.debug(ClassLoader.getSystemResource("4M.JPG").getPath());
        String url = baseUrl + "/upload";
        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("4M.JPG").getPath());

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userId", "1");
        body.add("file", fsr);
        String r = restTemplate.postForObject(url, body, String.class);
        logger.info(r);

    }

    //@Test
    //测试超过5M的文件
    public void uploadFor5M() {
        MyErrorHandler errorHandler = new MyErrorHandler();
        restTemplate.setErrorHandler(errorHandler);

        String url = baseUrl + "/upload";
        FileSystemResource fsr = new FileSystemResource(ClassLoader.getSystemResource("5M.JPG").getPath());

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userId", "1");
        body.add("file", fsr);
        String r = restTemplate.postForObject(url, body, String.class);
        logger.info(r);

    }

    //@Test
    //测试100次上单线程上传性能
    public void uploadFile100WTime() {
        // long times = 1000000;//100W
        long times = 3;
        long start = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            uploadFor4M();
        }
        long end = System.currentTimeMillis();
        Long useAV = (end - start) / times;
        logger.info("平均耗时:" + useAV + " 毫秒");
    }

}
