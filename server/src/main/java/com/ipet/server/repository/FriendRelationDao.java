package com.ipet.server.repository;

import com.ipet.server.domain.entity.FriendRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRelationDao extends JpaRepository<FriendRelation, Long> {

    //查询A与B的好友关系
    public FriendRelation findByUserIdAAndUserIdB(long userIdA, long userIdB);

    public List<FriendRelation> findByUserIdA(long userIdA);

    //@Query("select fr.id from FriendRelation fr where fr.userIdA=?1 and fr.userIdB=?2 ")
    // public FriendRelation isFriend(long userIdA, long userIdB);
}
