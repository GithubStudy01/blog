package com.chen.blog.controller;

import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.User;
import com.chen.blog.exception.BlogException;
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


@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public RespVo register(@Validated(value = {User.registerUserView.class}) User user,String token){
//        ,@NotBlank(message = "token不能为空！")
        //手动校验，后面会替换为 MD5盐值加密，如果使用jsr303，无法保存数据库
        String password = user.getPassword();
        if (password.length() < 8 || password.length() > 12) {
            throw new BlogException(WordDefined.PASSWORD_LENGTH_ERROR);
        }
        userService.register(user,token);
        return RespVo.success();
    }

    @GetMapping("/sendCode")
    @ResponseBody
    public RespVo sendCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone){
        Integer expire = userService.sendCode(phone);
        return RespVo.success(expire,null);
    }


    @PostMapping("/checkCode")
    @ResponseBody
    public RespVo checkCode(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone,@NotBlank String code){
        String token = userService.checkCode(phone, code);
        return RespVo.success(token,null);
    }


    @GetMapping("/checkPhoneUnique")
    @ResponseBody
    public RespVo checkPhoneUnique(@NotBlank(message = "电话号码不能为空") @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对") String phone){
        boolean unique = userService.checkPhoneUnique(phone);
        return RespVo.success(unique,null);
    }


    @JsonView(User.HotUserView.class)
    @GetMapping("/hot")
    @ResponseBody
    public RespVo getNameAndIdList(@PageableDefault(sort = {"goodSum","viewSum","commentSum"}, direction = Sort.Direction.DESC, page = 0, size = 10)Pageable pageable){
        Page<User> userList = userService.getList(pageable);
        return RespVo.success(userList,null);
    }

    @JsonView(User.SearchUserView.class)
    @GetMapping("/users")
    @ResponseBody
    public RespVo getList(@PageableDefault(sort = {"goodSum","viewSum","commentSum"}, direction = Sort.Direction.DESC, page = 0, size = 10)Pageable pageable,String nickname){
        Page<User> userList = userService.getListLikeNickname(pageable,nickname);
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
