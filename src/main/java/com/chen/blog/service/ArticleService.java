package com.chen.blog.service;

import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.Article;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.Comment;
import com.chen.blog.entity.User;
import com.chen.blog.exception.BlogException;
import com.chen.blog.repository.ArticleRepository;
import com.chen.blog.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAllByType(WordDefined.ARTICLE_OPEN,pageable);
    }

    public Article getById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            List<Comment> comments = commentRepository.findByArticleIs(article);
            comments = comments.parallelStream()
                    .sorted((c1, c2) -> {
                        int i = c1.getCId().compareTo(c2.getCId());
                        if (i == 0) {
                            return c1.getCreatetime().compareTo(c2.getCreatetime());
                        }
                        return i;
                    }).collect(Collectors.toList());
            article.setCommentList(comments);
            return article;
        }
        throw new BlogException(WordDefined.ARTICLE_NOT_FOUNT);
    }
}
