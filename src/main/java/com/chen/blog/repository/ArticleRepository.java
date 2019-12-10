package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {

//    List<Article> findAllBy


}
