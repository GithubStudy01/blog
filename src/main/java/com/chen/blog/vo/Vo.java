package com.chen.blog.vo;

import com.chen.blog.entity.*;

public class Vo {

    public interface BaseUserAndArticle extends User.BaseUserInfo , Article.BaseArticleInfo{}//文章列表视图

    public interface ArticleDetailsView extends Article.DetailsArticleView, Sort.ArticleSortView, Tag.ArticleTagView,Comment.ArticleCommentView,User.BaseUserInfo{}
}
