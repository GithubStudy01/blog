package com.chen.blog.service;

import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.Article;
import com.chen.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAllByType(WordDefined.ARTICLE_OPEN,pageable);
    }

}
