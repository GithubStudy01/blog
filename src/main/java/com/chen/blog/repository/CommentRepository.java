package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer> {


    List<Comment> findByArticleIs(Article article);


}
