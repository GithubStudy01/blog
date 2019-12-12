package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {

    Page<Article> findAllByType(Integer type, Pageable pageable);

    int countById(Long articleId);

}
