package com.chen.blog.repository;

import com.chen.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Integer> {




}
