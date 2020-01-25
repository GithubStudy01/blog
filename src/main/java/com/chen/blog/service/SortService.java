package com.chen.blog.service;

import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.Sort;
import com.chen.blog.entity.User;
import com.chen.blog.exception.BlogException;
import com.chen.blog.repository.BlogRepository;
import com.chen.blog.repository.SortRepository;
import com.chen.blog.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SortService {

    @Autowired
    private SortRepository sortRepository;

    @Autowired
    private BlogRepository blogRepository;


    public List<Sort> getList(Long blogId) {
        Blog blog = getBlog(blogId);
        return sortRepository.findByBlog(blog);
    }

    //需要登录
    public List<Sort> getList() {
        User user = SessionUtils.getUser();
        Blog blog = getBlog(user);
        return sortRepository.findByBlog(blog);
    }

    private Blog getBlog(Long blogId){
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        return getBlog(optionalBlog);
    }

    public Blog getBlog(User user){
        Optional<Blog>  optionalBlog = blogRepository.findByUser(user);
        return getBlog(optionalBlog);
    }
    //存在则返回，不存在或已经删除则抛出异常
    private Blog getBlog(Optional<Blog> optionalBlog){
        optionalBlog.orElseThrow(()->new BlogException(WordDefined.BLOG_NOT_FOUNT));
        Blog blog = optionalBlog.get();
        if (blog.getDeleteSign() == WordDefined.DELETE) {
            throw new BlogException(WordDefined.BLOG_ALREADY_DELETE);
        }
        return blog;
    }

    //需要登录
    @Transactional
    public void add(Sort sort) {
        User user = SessionUtils.getUser();
        Blog blog = getBlog(user);
        Sort tempSort = sortRepository.findBySortNameAndBlog(sort.getSortName(), blog);
        if (tempSort == null) {
            sort.setBlog(blog);
            sortRepository.save(sort);
            return;
        }
        throw new BlogException(WordDefined.SORT_ALREADY_EXIST);
    }
}
