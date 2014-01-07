
import com.ipet.server.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class TestUserRestController {

    private static final Logger logger = getLogger(TestAccountRestController.class);

    public static RestTemplate restTemplate = new RestTemplate();

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
        String url = baseUrl + "/upload";
        String filePath = "f:/4.JPG";
        FileSystemResource fsr = new FileSystemResource(filePath);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("name", "1.jpg");
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
        CustomResponseErrorHandler errorHandler = new CustomResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);

        String url = baseUrl + "/upload";
        String filePath = "f:/4M.JPG";
        FileSystemResource fsr = new FileSystemResource(filePath);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("name", "1.jpg");
        body.add("file", fsr);
        String r = restTemplate.postForObject(url, body, String.class);
        logger.info(r);

    }

    //@Test
    public void uploadFor5M() {
        CustomResponseErrorHandler errorHandler = new CustomResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);;

        String url = baseUrl + "/upload";
        String filePath = "f:/5M.JPG";
        FileSystemResource fsr = new FileSystemResource(filePath);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("name", "1.jpg");
        body.add("file", fsr);
        String r = restTemplate.postForObject(url, body, String.class);
        logger.info(r);

    }

}
