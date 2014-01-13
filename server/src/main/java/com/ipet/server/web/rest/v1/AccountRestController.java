package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.entity.User;
import com.ipet.server.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/account")
public class AccountRestController {

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(String username, String password) {
        logger.debug("username:" + username);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("用户名或密码为空");
        }

        User newUser = new User();
        newUser.setLoginName(username);
        newUser.setPlainPassword(password);
        //注册
        accountService.registerUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/availableUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> availableUsername(String username) {
        logger.debug("username:" + username);
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("无效参数");
        }
        return new ResponseEntity(accountService.availableUsername(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/availablePhone", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> availablePhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new RuntimeException("无效参数");
        }
        return new ResponseEntity(accountService.availablePhone(phone), HttpStatus.OK);
    }

    @RequestMapping(value = "/availableEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> availableEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("无效参数");
        }
        return new ResponseEntity(accountService.availableEmail(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/isNewUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> isNewUser(Long userId) {
        if (null == userId) {
            throw new RuntimeException("无效参数");
        }
        return new ResponseEntity(accountService.isNewUser(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> changePassword(String userId, String oldPassword, String newPassword) {
        if (null == userId || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            throw new RuntimeException("无效参数");
        }
        Long userIdValue = Long.parseLong(userId);
        accountService.changePassword(userIdValue, oldPassword, newPassword);

        return new ResponseEntity(HttpStatus.OK);
    }


    /*
     //for test
     @RequestMapping(value = "/body", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public ResponseEntity<?> createRequestParam(@RequestBody String body) {
     logger.debug("body");
     return new ResponseEntity(body, HttpStatus.OK);
     }
     @RequestMapping(value = "/create.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public ResponseEntity<?> create(@RequestBody User newUser) {
     logger.debug("createRequestBody");
     //注册
     accountService.registerUser(newUser);

     return new ResponseEntity(newUser, HttpStatus.CREATED);
     }
     @RequestMapping(value = "/create.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public ResponseEntity<?> createRequestParam(@RequestParam("username") String username, @RequestParam("password") String password) {
     logger.debug("createRequestParam");

     User newUser = new User();
     newUser.setLoginName(username);
     newUser.setPlainPassword(password);
     //注册
     accountService.registerUser(newUser);

     return new ResponseEntity(newUser, HttpStatus.CREATED);
     }

     @RequestMapping(value = "/createValid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public ResponseEntity<?> createValid(@Valid User newUser) {
     logger.debug("createValid");
     //注册
     accountService.registerUser(newUser);

     return new ResponseEntity(newUser, HttpStatus.CREATED);
     //http://johnathanmarksmith.com/spring/java/javaconfig/programming/spring%20java%20configuration/spring%20mvc/web/rest/resttemplate/2013/06/18/how-to-use-spring-resttemplate-to-post-data-to-a-web-service/
     }
     */
}
