package com.ipet.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ipet.server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    User findByLoginName(String loginName);

    Page<User> findAll(Long id, Pageable pageRequest);

    @Modifying
    @Query("delete from User u where u.id=?1")
    void deleteByUserId(Long id);
}
