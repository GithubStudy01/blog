package com.chen.blog.controller;

import com.chen.blog.entity.Article;
import com.chen.blog.entity.Collection;
import com.chen.blog.service.CollectionService;
import com.chen.blog.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("/collection")
@Validated
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/add/{id}")
    public RespVo addCollection(@PathVariable(value = "id") @NotNull(message = "文章id不能为空！") Long articleId) {
        collectionService.addCollection(articleId);
        return RespVo.success(null, null);
    }

    //已经登录
    @DeleteMapping("/delete/{id}")
    public RespVo delete(@PathVariable(value = "id") @NotNull(message = "文章id不能为空！") Long articleId) {
        collectionService.delete(articleId);
        return RespVo.success(null, null);
    }

/*    //已经登录
    @GetMapping
    public RespVo getList(@PageableDefault(sort = "createtime", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        Page<Collection> page = collectionService.getList(pageable);
        return RespVo.success(page, null);
    }*/
}
