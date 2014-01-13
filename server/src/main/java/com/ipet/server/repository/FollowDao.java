package com.ipet.server.repository;

import com.ipet.server.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowDao extends JpaRepository<Follow, Long> {

}
