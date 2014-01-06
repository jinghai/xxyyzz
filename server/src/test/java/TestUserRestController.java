
import com.ipet.server.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;
import org.junit.Test;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
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

    public static final String baseUrl = "http://localhost:8080/server/api/v1/user";

    //@Test
    public void getOneUser() {
        User user = restTemplate.getForObject(baseUrl + "/1", User.class);
        logger.info(user.toString());
    }

    //@Test
    public void getUsers() {
        //MultiValueMap<String, List<Long>> body = new LinkedMultiValueMap<String, List<Long>>();
        //Map<String, Object> urlVariables = new HashMap< String, Object>();
        User[] users = restTemplate.getForObject(baseUrl + "/listByIds?ids=1,2", User[].class);
        List<User> list = Arrays.asList(users);
        logger.info(list.toString());
    }

    @Test
    public void update() {
        User u = new User();
        u.setId(1l);
        u.setEmail("test@test.com");
        u.setPhone("111111111");
        u = restTemplate.postForObject(baseUrl + "/update", u, User.class);
        logger.info(u.toString());
    }

}
