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
@RequestMapping(value = "/v1/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    /**
     * 获取关注列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "listFollows", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> followList(String uid) {
        if (StringUtils.isEmpty(uid)) {
            throw new RuntimeException("无效参数");
        }
        long userId = Long.parseLong(uid);
        return contactService.getFollowUserList(userId);
    }

    /**
     * 获取粉丝列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "listFollowers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> followerList(String uid) {
        if (StringUtils.isEmpty(uid)) {
            throw new RuntimeException("无效参数");
        }
        long userId = Long.parseLong(uid);
        return contactService.getFollowerUserList(userId);
    }

    /**
     * 获取好友列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "listfriends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> friendList(String uid) {
        if (StringUtils.isEmpty(uid)) {
            throw new RuntimeException("无效参数");
        }
        long userId = Long.parseLong(uid);
        return contactService.getFriendUserList(userId);
    }

    /**
     * A关注B
     *
     * @param userIdA
     * @param userIdB
     * @return
     */
    @RequestMapping(value = "follow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean follow(String userIdA, String userIdB) {
        if (StringUtils.isEmpty(userIdA) || StringUtils.isEmpty(userIdB)) {
            throw new RuntimeException("无效参数");
        }
        Long aId = Long.valueOf(userIdA);
        Long bId = Long.valueOf(userIdB);
        contactService.follow(aId, bId);
        return true;
    }

    /**
     * A反关注B
     *
     * @param userIdA
     * @param userIdB
     * @return
     */
    @RequestMapping(value = "unfollow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean unfollow(String userIdA, String userIdB) {
        if (StringUtils.isEmpty(userIdA) || StringUtils.isEmpty(userIdB)) {
            throw new RuntimeException("无效参数");
        }
        Long aId = Long.valueOf(userIdA);
        Long bId = Long.valueOf(userIdB);
        contactService.unfollow(aId, bId);
        return true;
    }

}
