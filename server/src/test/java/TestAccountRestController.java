
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

    public static final String baseUrl = "http://localhost:8080/server/api/v1/account";

    @Test
    public void create() {
        //发送实体
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", "测试用户");
        request.add("password", "test");
        User r = restTemplate.postForObject(baseUrl + "/create", request, User.class);
        logger.debug(r.toString());
    }

}
