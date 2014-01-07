
import com.ipet.server.domain.User;
import org.junit.Test;
import org.slf4j.LoggerFactory;
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
public class TestAccountRestController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestAccountRestController.class);

    public static RestTemplate restTemplate = new RestTemplate();

    public static final String baseUrl = "http://localhost:8084/server/api/v1/account";

    @Test
    public void create() {
        //发送MultiValueMap参数
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", "测试用户");
        request.add("password", "test");
        User r = restTemplate.postForObject(baseUrl + "/create", request, User.class);
        logger.debug(r.toString());
    }

    //@Test
    public void availablePhone() {
        Boolean r = restTemplate.getForObject(baseUrl + "/availablePhone?phone=1111", Boolean.class);
        logger.debug(r.toString());
    }

    //@Test
    public void availableEmail() {
        Boolean r = restTemplate.getForObject(baseUrl + "/availableEmail?email=admin@gmail.com", Boolean.class);
        logger.debug(r.toString());
    }

    //@Test
    public void availableUsername() {
        Boolean r = restTemplate.getForObject(baseUrl + "/availableUsername?username=amdin", Boolean.class);
        logger.debug(r.toString());
    }

    //@Test
    public void isNewUser() {
        Boolean r = restTemplate.getForObject(baseUrl + "/isNewUser?userId=1", Boolean.class);
        logger.debug(r.toString());
    }

    // @Test
    public void changePassword() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("userId", "1");
        body.add("oldPassword", "admin");
        body.add("newPassword", "admin");
        String url = baseUrl + "/changePassword";
        Boolean r = restTemplate.postForObject(url, body, Boolean.class);
        logger.debug(r.toString());
    }

}
