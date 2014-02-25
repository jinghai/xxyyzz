package com.ipet.server.service;

import com.ipet.server.domain.UserState;
import com.ipet.server.domain.entity.Comment;
import com.ipet.server.domain.entity.Photo;
import com.ipet.server.domain.entity.User;
import com.ipet.server.repository.CommentDao;
import com.ipet.server.repository.PhotoDao;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.UserDao;
import com.ipet.server.util.ProjectUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 攒服务.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class CommentService {

    private static Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private PhotoDao photoDao;

    public List<Comment> getCommentList(String photoId, Integer pageNumber, Integer pageSize) {
        PageRequest pageR = ProjectUtil.buildPageRequest(pageNumber, pageSize, Sort.Direction.ASC, "createAt");
        Page<Comment> ret = getCommentDao().findByPhotoId(photoId, pageR);
        return ret.getContent();
    }

    @Transactional(readOnly = false)
    public Comment comment(String photoId, String userId, String text) {
        User user = getUserDao().findByIdAndUserState(userId, UserState.ENABLE);
        if (user == null) {
            throw new RuntimeException("无效用户");
        }

        Comment comment = new Comment();
        comment.setText(text);
        comment.setPhotoId(photoId);
        comment.setUserName(user.getDisplayName());
        comment.setUserId(userId);
        getCommentDao().save(comment);

        Photo photo = getPhotoDao().findOne(photoId);
        photo.setCommentCount(photo.getCommentCount() + 1);
        getPhotoDao().save(photo);

        user.setCommentCount(user.getCommentCount() + 1);
        getUserDao().save(user);
        return comment;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the commentDao
     */
    public CommentDao getCommentDao() {
        return commentDao;
    }

    /**
     * @param commentDao the commentDao to set
     */
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /**
     * @return the photoDao
     */
    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    /**
     * @param photoDao the photoDao to set
     */
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

}
