package com.ipet.web.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/oauth2")
public class OAuthRestController {

    private static final Logger logger = LoggerFactory.getLogger(OAuthRestController.class);

    /**
     * 认证
     *
     */
    @RequestMapping(value = "authorize", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void authorize() {
    }

    @RequestMapping(value = "token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void token() {
    }

    @RequestMapping(value = "refresh_token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void refresh_token() {
    }

}
