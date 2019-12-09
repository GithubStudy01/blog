package com.chen.blog.controller;

import com.chen.blog.entity.TestUser;
import com.chen.blog.entity.User;
import com.chen.blog.service.UserService;
import com.chen.blog.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;


@Controller
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    @ResponseBody
    public RespVo register(@Validated(value = {User.registerUserView.class}) User user){
        try{
            userService.register(user);
        }catch (Exception e){
            return RespVo.fail(e.getMessage(),null);
        }
        return RespVo.success();
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public RespVo checkCode(@NotBlank String code){
//        userService.register(user);
        return RespVo.success();
    }



}
