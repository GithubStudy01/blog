package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article,Long> {




}
