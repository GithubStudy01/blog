package com.chen.blog.repository;

import com.chen.blog.entity.Blog;

import com.chen.blog.entity.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SortRepository extends JpaRepository<Sort,Integer> {

    List<Sort> findByBlog(Blog blog);



}
