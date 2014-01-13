package com.ipet.server.repository;

import com.ipet.server.domain.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerDao extends JpaRepository<Follower, Long> {

}
