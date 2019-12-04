package com.chen.blog.vo;

import com.chen.blog.entity.Blog;
import com.chen.blog.entity.User;

public class Vo {

    public interface UserAndBlog extends User.OnlyUserInfo , Blog.OnlyBlogInfo {}


}
