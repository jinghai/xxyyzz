package com.ipet.server.repository;

import com.ipet.server.domain.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDao extends JpaRepository<App, String> {

    public App findByAppKey(String appKey);
}
