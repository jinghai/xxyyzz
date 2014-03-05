package com.ipet.web.rest.v1;

import com.ipet.server.domain.entity.User;
import com.ipet.server.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author xiaojinbghai
 */
@Controller
@RequestMapping(value = "/v1/user")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户，仅用于调试
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> list() {
        return userService.getAllUser();
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * 批量获取用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/listByIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> listByIds(String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new RuntimeException("无效参数");
        }
        List<String> idsArray = new ArrayList<String>();
        if (!ids.contains(",")) {
            idsArray.add(ids);
        } else {
            String[] idsList = StringUtils.split(ids, ",");
            logger.debug("idsList:" + idsList[0]);
            for (int i = 0, len = idsList.length; i < len; i++) {
                idsArray.add(idsList[i]);
            }
        }

        return userService.getUserByIds(idsArray);
    }

    /**
     * 只更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateInfo(@RequestBody User user) {
        if (null == user.getId()) {
            throw new RuntimeException("无效参数");
        }
        logger.debug("user:" + user.toString());
        return userService.updateUserInfo(user);
    }

    /**
     * 只更新用户头像
     *
     * @param userId
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User upload(String userId, /*@RequestParam*/ MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isEmpty(userId)) {
            throw new RuntimeException("无效参数");
        }
        User user = userService.updateAvatar(userId, file);
        return user;
    }

    /*
     //更新用户信息和头像信息,未成功，只能映射基础数据类型，不能User
     @RequestMapping(value = "/update2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public User update2(@RequestParam User user, @RequestParam(required = false) MultipartFile file) throws IOException {
     if (null == user.getId()) {
     throw new RuntimeException("无效参数");
     }
     logger.debug("user:" + user.toString());
     return userService.updateUserAndAvatar(user, file);
     }
     */
}
