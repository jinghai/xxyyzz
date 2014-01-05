package com.ipet.server.web.api.rest.v1;

import com.ipet.server.domain.User;
import com.ipet.server.service.AccountService;
import javax.validation.Valid;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Task的Restful API的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountRestController {

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/create.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(String username, String password) {
        logger.debug("username:" + username);
        User newUser = new User();
        newUser.setLoginName(username);
        newUser.setPlainPassword(password);
        //注册
        accountService.registerUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
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
