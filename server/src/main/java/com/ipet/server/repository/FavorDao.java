package com.ipet.server.repository;

import com.ipet.server.domain.entity.Favor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorDao extends JpaRepository<Favor, String> {

    public List<Favor> findByPhotoId(String photoId);
}
