package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Collection;
import com.chen.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CollectionRepository extends JpaRepository<Collection,Integer> {

    Collection findAllByArticleAndUser(Article article, User user);

    void deleteAllByArticleAndUser(Article article,User user);

}
