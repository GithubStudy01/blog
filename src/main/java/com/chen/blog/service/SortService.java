package com.chen.blog.service;

import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.Sort;
import com.chen.blog.exception.BlogException;
import com.chen.blog.repository.BlogRepository;
import com.chen.blog.repository.SortRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //存在则返回，不存在或已经删除则抛出异常
    private Blog getBlog(Long blogId){
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        optionalBlog.orElseThrow(()->new BlogException(WordDefined.BLOG_NOT_FOUNT));
        Blog blog = optionalBlog.get();
        if (blog.getDeleteSign() == WordDefined.DELETE) {
            throw new BlogException(WordDefined.BLOG_ALREADY_DELETE);
        }
        return blog;
    }

}
