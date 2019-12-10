package com.chen.blog.vo;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.User;

public class Vo {

    public interface UserAndBlog extends User.OnlyUserInfo , Blog.OnlyBlogInfo {}

    public interface BaseUserAndArticle extends User.BaseUserInfo , Article.BaseArticleInfo{}

}
