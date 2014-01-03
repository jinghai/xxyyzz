
import com.ipet.server.domain.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.util.thread.Timeout.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.web.client.RestTemplate;

import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.category.Smoke;

/**
 * 任务管理的功能测试, 测试页面JavaScript及主要用户故事流程.
 *
 * @author calvin
 */
public class Rest extends BaseFunctionalTestCase {

    private final RestTemplate restTemplate = new RestTemplate();

    private final JsonMapper jsonMapper = new JsonMapper();

    private static class TaskList extends ArrayList<Task> {
    };

    private static String resoureUrl;

    @BeforeClass
    public static void initUrl() {
        resoureUrl = baseUrl + "/api/v1/account/create.json";
    }

    /**
     * 创建.
     */
    @Test
    @Category(Smoke.class)
    public void getTask() {
        //create
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", "test");
        param.put("password", "test");
        restTemplate.postForObject(baseUrl, null, User.class, param);

    }

}
