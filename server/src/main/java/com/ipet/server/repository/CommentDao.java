package com.ipet.server.repository;

import com.ipet.server.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
