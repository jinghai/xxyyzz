package com.ipet.server.service;

import com.ipet.server.domain.UserState;
import com.ipet.server.domain.entity.FollowRelation;
import com.ipet.server.domain.entity.FriendRelation;
import com.ipet.server.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.FollowRelationDao;
import com.ipet.server.repository.FriendRelationDao;
import com.ipet.server.repository.UserDao;
import java.util.ArrayList;
import java.util.List;

/**
 * 人脉管理.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class ContactService {

    private static Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowRelationDao followRelationDao;

    @Autowired
    private FriendRelationDao friendRelationDao;

    /**
     * 获取关注列表
     *
     * @param userId
     * @return
     */
    public List<User> getFollowUserList(long userId) {
        List<FollowRelation> frlist = getFollowRelationDao().findByUserIdA(userId);
        List<Long> ids = new ArrayList<Long>(frlist.size());
        if (frlist.size() > 0) {
            for (FollowRelation fr : frlist) {
                ids.add(fr.getUserIdB());
            }
            return getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        } else {
            return new ArrayList<User>(0);
        }
    }

    /**
     * 获取粉丝列表
     *
     * @param userId
     * @return
     */
    public List<User> getFollowerUserList(long userId) {
        List<FollowRelation> frlist = getFollowRelationDao().findByUserIdB(userId);
        List<Long> ids = new ArrayList<Long>(frlist.size());
        if (frlist.size() > 0) {
            for (FollowRelation fr : frlist) {
                ids.add(fr.getUserIdA());
            }
            return getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        } else {
            return new ArrayList<User>(0);
        }
    }

    /**
     * 获取朋友列表
     *
     * @param userId
     * @return
     */
    public List<User> getFriendUserList(long userId) {

        List<FriendRelation> frlist = getFriendRelationDao().findByUserIdA(userId);
        List<Long> ids = new ArrayList<Long>(frlist.size());
        if (frlist.size() > 0) {
            for (FriendRelation fr : frlist) {
                ids.add(fr.getUserIdB());
            }
            return getUserDao().findByIdInAndUserState(ids, UserState.ENABLE);
        } else {
            return new ArrayList<User>(0);
        }
    }

    /**
     * A关注B
     *
     * @param userIdA
     * @param userIdB
     */
    @Transactional(readOnly = false)
    public void follow(long userIdA, long userIdB) {

        User userA = this.getUserDao().findByIdAndUserState(userIdA, UserState.ENABLE);
        User userB = this.getUserDao().findByIdAndUserState(userIdB, UserState.ENABLE);
        if (userA == null || userB == null) {
            throw new RuntimeException("无效用户");
        }
        //A是否关注过B
        boolean aHasFollowdB = getFollowRelationDao().findByUserIdAAndUserIdB(userIdA, userIdB) != null;
        if (aHasFollowdB) {
            logger.warn("增加一个已存在的关注关系");
            return;
        }
        //建立关注关系
        FollowRelation follow = new FollowRelation();
        follow.setUserIdA(userIdA);
        follow.setUserIdB(userIdB);
        getFollowRelationDao().save(follow);
        //B是否关注过A
        boolean bHasFollowdA = getFollowRelationDao().findByUserIdAAndUserIdB(userIdB, userIdA) != null;
        //如果B关注了A，则建立朋友关系
        if (bHasFollowdA) {
            FriendRelation friendRA = new FriendRelation();
            friendRA.setUserIdA(userIdA);
            friendRA.setUserIdB(userIdB);
            FriendRelation friendRB = new FriendRelation();
            friendRB.setUserIdA(userIdB);
            friendRB.setUserIdB(userIdA);
            getFriendRelationDao().save(friendRA);
            getFriendRelationDao().save(friendRB);
        }
    }

    /**
     * 反关注
     *
     * @param userIdA
     * @param userIdB
     */
    @Transactional(readOnly = false)
    public void unfollow(long userIdA, long userIdB) {
        User userA = this.getUserDao().findByIdAndUserState(userIdA, UserState.ENABLE);
        User userB = this.getUserDao().findByIdAndUserState(userIdB, UserState.ENABLE);
        if (userA == null || userB == null) {
            throw new RuntimeException("无效用户");
        }
        //A与B的关注关系
        FollowRelation followAB = getFollowRelationDao().findByUserIdAAndUserIdB(userIdA, userIdB);
        if (followAB == null) {
            logger.warn("删除一个不存在的关注关系");
            return;
        }
        //解除关注关系
        getFollowRelationDao().delete(followAB);
        FriendRelation friendRA = getFriendRelationDao().findByUserIdAAndUserIdB(userIdA, userIdB);
        FriendRelation friendRB = getFriendRelationDao().findByUserIdAAndUserIdB(userIdB, userIdA);

        //解除朋友关系
        if (friendRA != null) {
            getFriendRelationDao().delete(friendRA);
        }
        if (friendRB != null) {
            getFriendRelationDao().delete(friendRB);
        }

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
     * @return the followRelationDao
     */
    public FollowRelationDao getFollowRelationDao() {
        return followRelationDao;
    }

    /**
     * @param followRelationDao the followRelationDao to set
     */
    public void setFollowRelationDao(FollowRelationDao followRelationDao) {
        this.followRelationDao = followRelationDao;
    }

    /**
     * @return the friendRelationDao
     */
    public FriendRelationDao getFriendRelationDao() {
        return friendRelationDao;
    }

    /**
     * @param friendRelationDao the friendRelationDao to set
     */
    public void setFriendRelationDao(FriendRelationDao friendRelationDao) {
        this.friendRelationDao = friendRelationDao;
    }

}
