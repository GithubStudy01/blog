package com.chen.blog.controller;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Sort;
import com.chen.blog.service.SortService;
import com.chen.blog.vo.RespVo;
import com.chen.blog.vo.Vo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/sort")
@Validated
public class SortController {

    @Autowired
    private SortService sortService;


    @GetMapping("/sorts")
    @ResponseBody
    @JsonView({Sort.SortInfoView.class})
    public RespVo getList(@NotNull Long blogId){
        List<Sort> sorts = sortService.getList(blogId);
        return RespVo.success(sorts,null);
    }


//    @GetMapping("/article/{id}")
//    @ResponseBody
//    @JsonView({Sort.SortInfoView.class})
//    public RespVo getArticleListBySortId(@PathVariable("id") @NotNull Long id){
//        sortService.getById(id);
//
//
//        Article article = articleService.getById(id);
//        return RespVo.success(article,null);
//    }


}
