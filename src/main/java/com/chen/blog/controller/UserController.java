package com.chen.blog.controller;

import com.chen.blog.entity.User;
import com.chen.blog.service.UserService;
import com.chen.blog.vo.RespVo;
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
import java.util.List;


@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public RespVo register(@Validated(value = {User.registerUserView.class}) User user,@NotBlank String token){
        try{
            userService.register(user,token);
        }catch (Exception e){
            return RespVo.fail(e.getMessage(),null);
        }
        return RespVo.success();
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public RespVo sendCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone){
        userService.sendCode(phone);
        return RespVo.success();
    }


    @RequestMapping("/checkCode")
    @ResponseBody
    public RespVo checkCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone,@NotBlank String code){
        userService.checkCode(phone,code);
        return RespVo.success();
    }


    @RequestMapping("/checkPhoneUnique")
    @ResponseBody
    public RespVo checkPhoneUnique(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone){
        boolean unique = userService.checkPhoneUnique(phone);
        return RespVo.success(unique,null);
    }


    @JsonView(User.TopUserView.class)
    @GetMapping("/getList")
    @ResponseBody
    public RespVo getNameAndIdList(@PageableDefault(sort = {"goodSum","viewSum","commentSum"}, direction = Sort.Direction.DESC, page = 0, size = 10)Pageable pageable){
        Page<User> userList = userService.getList(pageable);
        return RespVo.success(userList,null);
    }

    @JsonView(User.SearchUserView.class)
    @GetMapping("/users")
    @ResponseBody
    public RespVo getList(@PageableDefault(sort = {"goodSum","viewSum","commentSum"}, direction = Sort.Direction.DESC, page = 0, size = 10)Pageable pageable){
        Page<User> userList = userService.getList(pageable);
        return RespVo.success(userList,null);
    }

    @JsonView(User.HomeUserView.class)
    @GetMapping("/users/{id}")
    @ResponseBody
    public RespVo getById(@PathVariable(value = "id")@NotNull Long id){
        User user = userService.getById(id);
        return RespVo.success(user,null);
    }


}
