package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.entity.Comment;
import com.ipet.server.domain.entity.Favor;
import com.ipet.server.service.CommentService;
import java.io.IOException;
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
 * 评论
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment create(String uid, String photoId, String text) throws IOException {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(photoId) || StringUtils.isEmpty(text)) {
            throw new RuntimeException("非法参数");
        }
        return commentService.comment(photoId, uid, text);
    }

    @RequestMapping(value = "listPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> getlistPage(String photoId, String pageNumber, String pageSize) throws IOException {
        if (StringUtils.isEmpty(photoId) || StringUtils.isEmpty(pageNumber) || StringUtils.isEmpty(pageSize)) {
            throw new RuntimeException("非法参数");
        }

        Integer page = Integer.valueOf(pageNumber);
        Integer size = Integer.valueOf(pageSize);

        return commentService.getCommentList(photoId, page, size);
    }

}
