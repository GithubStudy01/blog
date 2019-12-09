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
import javax.validation.constraints.Pattern;


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

    @RequestMapping("/sendCode")
    @ResponseBody
    public RespVo sendCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone){
        userService.sendCode(phone);
//        userService.register(user);
        return RespVo.success();
    }


    @RequestMapping("/checkCode")
    @ResponseBody
    public RespVo checkCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone,@NotBlank String code){
        userService.checkCode(phone,code);
//        userService.register(user);
        return RespVo.success();
    }



}
