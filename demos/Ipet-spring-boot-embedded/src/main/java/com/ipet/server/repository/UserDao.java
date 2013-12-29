package com.ipet.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ipet.server.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    User findByName(String name);
}
