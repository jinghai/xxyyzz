package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.entity.User;
import com.ipet.server.service.ContactService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 人脉rest
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/photo")
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    //@Autowired
    //private ContactService contactService;
    /**
     * 发布图片
     *
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void followList(String uid) {

    }

}
