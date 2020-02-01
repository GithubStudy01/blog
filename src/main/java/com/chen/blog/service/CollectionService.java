package com.chen.blog.service;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Collection;
import com.chen.blog.entity.User;
import com.chen.blog.repository.CollectionRepository;
import com.chen.blog.utils.OthersUtils;
import com.chen.blog.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ArticleService articleService;

    @Transactional
    public void addCollection(Long articleId) {
        Article article = articleService.getById(articleId);
        User user = SessionUtils.getUser();
        Collection collection = combinData(user, article);
        LocalDateTime createTime = OthersUtils.getCreateTime();
        collection.setCreatetime(createTime);
        collectionRepository.save(collection);
    }

    @Transactional
    public void delete(Long articleId) {
        Article article = articleService.getById(articleId);
        User user = SessionUtils.getUser();
        collectionRepository.deleteAllByArticleAndUser(article, user);
    }


    private Collection combinData(User user, Article article) {
        Collection collection = new Collection();
        collection.setArticle(article);
        collection.setUser(user);
        return collection;
    }

}
