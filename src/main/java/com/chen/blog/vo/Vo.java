package com.chen.blog.vo;

import com.chen.blog.entity.*;

public class Vo {

    public interface BaseUserAndArticle extends User.BaseUserInfo , Article.BaseArticleInfo{}//文章列表视图

    public interface ArticleDetailsNoCommentView extends Article.DetailsArticleView, Sort.ArticleSortView, Tag.ArticleTagView,User.BaseUserInfo{}

    public interface CommentView extends User.BaseUserInfo,Comment.ArticleCommentView{}

    public interface ReplyView extends  User.BaseUserInfo,Comment.ReplyCommentView{}
}
