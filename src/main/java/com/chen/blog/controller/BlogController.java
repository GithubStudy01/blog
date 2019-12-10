package com.chen.blog.controller;

import com.chen.blog.entity.Blog;
import com.chen.blog.entity.User;
import com.chen.blog.service.BlogService;
import com.chen.blog.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog")
@Validated
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/get")
    @ResponseBody
    public RespVo getList(Blog blog){
        try{
//            userService.register(user,token);
        }catch (Exception e){
            return RespVo.fail(e.getMessage(),null);
        }
        return RespVo.success();
    }



}
