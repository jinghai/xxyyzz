package com.ipet.server.repository;

import com.ipet.server.domain.entity.User;
import com.ipet.server.domain.UserState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long> {

    public List<User> findByIdInAndUserState(List<Long> ids, UserState state);

    public User findByIdAndUserState(Long Id, UserState state);

    public User findByLoginNameAndUserState(String loginName, UserState state);

    public User findByEmailAndUserState(String email, UserState state);

    public User findByPhoneAndUserState(String phone, UserState state);

    @Query("select user.id from User user where user.loginName=?1 and user.userState=?2")
    public Long getUserIdByLoginNameAndUserState(String loginName, UserState state);

    @Query("select user.id from User user where user.email=?1 and user.userState=?2")
    public Long getUserIdByEmailAndUserState(String email, UserState state);

    @Query("select user.id from User user where user.phone=?1 and user.userState=?2")
    public Long getUserIdByPhoneAndUserState(String phone, UserState state);

    @Query("select user.loginNum from User user where user.id=?1 and user.userState=?2")
    public Long getLoginNumByIdAndUserState(Long userId, UserState state);
}
