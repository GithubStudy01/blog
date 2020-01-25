package com.chen.blog.repository;

import com.chen.blog.entity.Blog;
import com.chen.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    Optional<Blog> findByUser(User user);



}
