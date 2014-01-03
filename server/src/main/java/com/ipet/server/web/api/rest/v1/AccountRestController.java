package com.ipet.server.web.api.rest.v1;

import com.ipet.server.domain.User;
import com.ipet.server.service.AccountService;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Task的Restful API的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountRestController {

    private static Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/create.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable String username, @PathVariable String password) {
        logger.debug("into /api/v1/account/create.json:" + username + "," + password);
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setLoginName(username);
        user.setPlainPassword(password);
        //注册
        accountService.registerUser(user);

        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
