package com.ipet.server.web.rest.v1;

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
@RequestMapping(value = "/v1/health")
public class HealthRestController {

    private static final Logger logger = LoggerFactory.getLogger(HealthRestController.class);

    /**
     * 监控检查
     *
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void health() {
    }

}
