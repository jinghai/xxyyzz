package com.ipet.server.repository;

import com.ipet.server.domain.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo, String> {

}
