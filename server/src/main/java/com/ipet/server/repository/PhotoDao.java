package com.ipet.server.repository;

import com.ipet.server.domain.entity.Photo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo, String> {

    public Page<Photo> findByCreateAtAfter(Date date, Pageable pageRequest);

    public Page<Photo> findByCreateAtAfterAndUserId(Date date, String userId, Pageable pageRequest);

    public Page<Photo> findByCreateAtAfterAndUserIdIn(Date date, List<String> ids, Pageable pageRequest);
}
