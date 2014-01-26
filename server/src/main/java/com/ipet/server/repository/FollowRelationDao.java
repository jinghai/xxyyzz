package com.ipet.server.repository;

import com.ipet.server.domain.entity.FollowRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRelationDao extends JpaRepository<FollowRelation, String> {

    /**
     * 查询A与B的关注关系
     *
     * @param userIdA
     * @param userIdB
     * @return
     */
    public FollowRelation findByUserIdAAndUserIdB(String userIdA, String userIdB);

    public List<FollowRelation> findByUserIdA(String userId);

    public List<FollowRelation> findByUserIdB(String userId);

    /**
     * A是否关注了B
     *
     * @param userIdA
     * @param userIdB
     * @return
     */
    //@Query("select fr.id from FollowRelation fr where fr.userIdA=?1 and fr.userIdB=?2")
    //public String isFollowd(long userIdA, long userIdB);
    /**
     * A是否是B的粉丝
     *
     * @param userIdA
     * @param userIdB
     * @return
     */
    //@Query("select fr.id from FollowRelation fr where fr.userIdB=?1 and fr.userIdA=?2")
    //public String isFollower(long userIdA, long userIdB);
}
