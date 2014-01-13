package com.ipet.server.repository;

import com.ipet.server.domain.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorDao extends JpaRepository<Favor, Long> {

}
