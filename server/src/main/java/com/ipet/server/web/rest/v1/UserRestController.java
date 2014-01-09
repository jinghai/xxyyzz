package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.User;
import com.ipet.server.service.FileUploadService;
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
    @Autowired
    private FileUploadService uploder;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> list() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/listByIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> listByIds(String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new RuntimeException("无效参数");
        }
        List<Long> idsArray = new ArrayList<Long>();
        if (!ids.contains(",")) {
            idsArray.add(Long.parseLong(ids));
        } else {
            String[] idsList = StringUtils.split(ids, ",");
            logger.debug("idsList:" + idsList[0]);
            for (int i = 0, len = idsList.length; i < len; i++) {
                idsArray.add(Long.valueOf(idsList[i]));
            }
        }

        return userService.getUserByIds(idsArray);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User update(@RequestBody User user) {
        if (null == user.getId()) {
            throw new RuntimeException("无效参数");
        }
        logger.debug("user:" + user.toString());
        return userService.updateUserInfo(user);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String upload(String userId, /*@RequestParam*/ MultipartFile file) throws IOException {
        long uid = Long.parseLong(userId);
        if (file.isEmpty()) {
            throw new RuntimeException("无效参数");
        }
        uploder.uploadAvatar(file, uid);
        return "";
    }

}
