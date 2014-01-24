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
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private UserDao userDao;

}
