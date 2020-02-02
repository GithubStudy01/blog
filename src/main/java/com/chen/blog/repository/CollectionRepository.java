package com.chen.blog.repository;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Collection;
import com.chen.blog.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CollectionRepository extends JpaRepository<Collection,Integer> {

    Collection findAllByArticleAndUser(Article article, User user);

    void deleteAllByArticleAndUser(Article article,User user);


//    @Query(value = "select new com.chen.blog.entity.Collection(c.id,c.createtime,a.title,a.id) from collection c join article a on c.articleId=a.id where c.user_id = :userId and a.type = :type")
//    Page<Collection> findAllByCondition(@Param("userId") Long userId, @Param("type") Integer type, Pageable pageable);

}
