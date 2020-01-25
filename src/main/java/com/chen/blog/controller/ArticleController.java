package com.chen.blog.controller;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Comment;
import com.chen.blog.service.ArticleService;
import com.chen.blog.vo.RespVo;
import com.chen.blog.vo.Vo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * page=0&size=4&sort=createtime,desc
     * sort=firstname&sort=lastname,desc
     * createtime
     *
     * @param pageable
     * @return
     */
    @GetMapping("/articles")
    @JsonView({Vo.BaseUserAndArticle.class})
    public RespVo getList(@PageableDefault(sort = "createtime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        Page<Article> page = articleService.getList(pageable);
        return RespVo.success(page, null);
    }


    /**
     * page=0&size=4&sort=createtime,desc
     * sort=firstname&sort=lastname,desc
     * createtime
     * @param pageable
     * @param title
     * @param limitTimeType
     * @return
     */
    @GetMapping("/search")
    @JsonView({Vo.BaseUserAndArticle.class})
    public RespVo getListBySearch(@PageableDefault(sort = {"goodTimes", "createtime", "commentTimes", "viewTimes"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable, String title, @RequestParam(defaultValue = "0",required = false) @Pattern(regexp = "^[0-3]$", message = "非法状态值！")String limitTimeType) {
        Page<Article> page = articleService.getListBySearch(pageable, title, limitTimeType);
        return RespVo.success(page, null);
    }

    @GetMapping("/hot")
    @JsonView({Vo.HotArticleView.class})
    public RespVo hotList(@PageableDefault(sort = {"goodTimes", "commentTimes", "viewTimes", "createtime"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        Page<Article> page = articleService.getList(pageable);
        return RespVo.success(page, null);
    }


    @GetMapping("/articles/{id}")
    @JsonView({Vo.ArticleDetailsNoCommentView.class})
    public RespVo getById(@PathVariable("id") @NotNull Long id) {
        Article article = articleService.getById(id);
        return RespVo.success(article, null);
    }

    @GetMapping("/sort")
    @JsonView({Vo.BaseUserAndArticle.class})
    public RespVo getListBySortId(@PageableDefault(sort = "createtime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
                                  @NotNull(message = "分类id不能为空！") Integer sortId) {
        Page<Article> article = articleService.getListBySortId(sortId, pageable);
        return RespVo.success(article, null);
    }


    @GetMapping("/recent/{id}")
    @JsonView({Article.RecentUpdatesView.class})
    public RespVo getRecentUpdatesList(@PageableDefault(sort = "createtime", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable pageable, @PathVariable(value = "id") @NotNull(message = "用户id不能为空！") Long userId) {
        Page<Article> page = articleService.getArticleListByUserId(pageable, userId);
        return RespVo.success(page, null);
    }


    @GetMapping("/user/{id}")
    @JsonView({Vo.BaseUserAndArticleWithOverhead.class})
    public RespVo getArticleListByUserId(@PageableDefault(sort = {"overhead", "overheadTime", "createtime"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable, @PathVariable(value = "id") @NotNull(message = "用户id不能为空！") Long userId) {
        Page<Article> page = articleService.getArticleListByUserId(pageable, userId);
        return RespVo.success(page, null);
    }

    //已经登录
    @PutMapping("/changeOverhead")
    @ResponseBody
    public RespVo changeOverhead(@NotNull(message = "文章id不能为空！") Long articleId, @NotNull(message = "是否顶置标志不能为空！") Integer overhead) {
        int i = articleService.changeOverhead(articleId, overhead);
        return RespVo.success(i, null);
    }


    //已经登录
    @PutMapping("/changeType")
    public RespVo changeType(@NotNull(message = "文章id不能为空！") Long articleId, @NotNull(message = "是否公开标志不能为空！") Integer type) {
        int i = articleService.changeType(articleId, type);
        return RespVo.success(i, null);
    }

    //已经登录
    @DeleteMapping("/delete/{id}")
    public RespVo delete(@PathVariable(value = "id") @NotNull(message = "文章id不能为空！") Long articleId) {
        articleService.delete(articleId);
        return RespVo.success(null, null);
    }

    @PostMapping("/add")
    public RespVo addArticle(@Validated(value = {Article.AddArticleView.class})Article article,String tagName,@Pattern(regexp = "-?[1-9]\\d*",message = "分类参数有误！")String sortId) {
        articleService.addArticle(article,tagName,sortId);
        return RespVo.success(null, null);
    }

}
