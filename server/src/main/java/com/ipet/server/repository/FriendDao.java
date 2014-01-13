package com.ipet.server.repository;

import com.ipet.server.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendDao extends JpaRepository<Friend, Long> {

}
