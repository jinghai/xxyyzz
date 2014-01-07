
import com.ipet.server.domain.User;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
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

}
