package com.chen.blog.controller;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.TestUser;
import com.chen.blog.entity.User;
import com.chen.blog.service.ArticleService;
import com.chen.blog.vo.RespVo;
import com.chen.blog.vo.Vo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *  page=0&size=4&sort=createtime,desc
     *  sort=firstname&sort=lastname,desc
     * createtime
     * @param pageable
     * @return
     */
    @GetMapping("/articles")
    @ResponseBody
    @JsonView({Vo.BaseUserAndArticle.class})
    public RespVo getList(@PageableDefault(sort = "createtime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable){
        Page<Article> page = articleService.getList(pageable);
        return RespVo.success(page,null);
    }


    @GetMapping("/articles/{id}")
    @ResponseBody
    @JsonView({Vo.ArticleDetailsNoCommentView.class})
    public RespVo getById(@PathVariable("id") @NotNull Long id){
        Article article = articleService.getById(id);
        return RespVo.success(article,null);
    }



}
