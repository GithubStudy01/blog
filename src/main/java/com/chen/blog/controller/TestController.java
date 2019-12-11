package com.chen.blog.controller;

import com.chen.blog.entity.Blog;
import com.chen.blog.entity.TestUser;
import com.chen.blog.entity.User;
import com.chen.blog.repository.TestUserRepository;
import com.chen.blog.repository.UserRepository;
import com.chen.blog.vo.RespVo;
import com.chen.blog.vo.Vo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Validated
public class TestController {

    @RequestMapping("/test")
    public String test(){
        System.out.println(">>>>");
        
        return "test";
    }

    @RequestMapping("/add")
    @ResponseBody
    public RespVo addUser(@Validated(value = {TestUser.AddUserView.class}) TestUser user){
        System.out.println(user);
        return RespVo.success();
    }



    @RequestMapping("/update")
    @ResponseBody
    public RespVo updateUser(@Validated(value = {TestUser.UpdateUserView.class}) TestUser user){
        System.out.println(user);
        return RespVo.success();
    }



    @RequestMapping("/get")
    @ResponseBody
    @JsonView(TestUser.GetUserView.class)
    public List<Object> getUser(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);
        final ArrayList<Object> list = new ArrayList<>();
        list.add(new TestUser(1,"laowang","123456","揭阳市"));
        list.add("测试");
        return list;
    }


    @RequestMapping("/get3")
    @ResponseBody
    @JsonView(TestUser.GetUserView.class)
    public RespVo<List> getUser3(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);
        final ArrayList<Object> list = new ArrayList<>();
        list.add(new TestUser(3,"laowang","123456","揭阳市"));
        list.add("测试");
        return RespVo.success(list,null);
    }

    @RequestMapping("/get4")
    @ResponseBody
    @JsonView(TestUser.GetUserView.class)
    public RespVo getUser4(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);

        final ArrayList<Object> list = new ArrayList<>();
        list.add(new TestUser(4,"laowang","123456","揭阳市"));
        list.add("测试");
        return RespVo.success(list,null);
    }

    @RequestMapping("/get5")
    @ResponseBody
    @JsonView(TestUser.GetUserView.class)
    public RespVo<TestUser> getUser5(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);

        return RespVo.success(new TestUser(5,"laowang","123456","揭阳市"),null);
    }

    @RequestMapping("/get6")
    @ResponseBody
    @JsonView(TestUser.GetAllNotPassword.class)
    public RespVo<TestUser> getUser6(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);

        return RespVo.success(new TestUser(6,"laowang","123456","揭阳市"),null);
    }

    @RequestMapping("/get7")
    @ResponseBody
    @JsonView({TestUser.OnlyGetAddress.class})
    public RespVo<TestUser> getUser76(@NotNull(message="用户id不能为空！") Integer id){
        System.out.println("id:"+id);

        return RespVo.success(new TestUser(7,"laowang","123456","揭阳市"),null);
    }

    @Autowired
    private TestUserRepository testUserRepository;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public RespVo<TestUser> getUserById(@PathVariable("id") Integer id){
        final TestUser one = testUserRepository.getOne(1);
        return RespVo.success(one,null);
    }

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/findById")
    @ResponseBody

    public RespVo<User> getUser(){
        final User user = userRepository.findById(1L).get();
        System.out.println(user.getNickname());
//        final Blog blog = user.getBlog();
//        System.out.println(blog.getBlogName());
        System.out.println(">>>");
//        user.setBlog(blog);
//        System.out.println("***");
        
        return RespVo.success(user,null);
    }



    @RequestMapping("/userpage")
    @ResponseBody
//    @JsonView({User.OnlyUserInfo.class})
    public RespVo getUserPage(){
        int pageNo = 0;
        int pageSize = 3;//每页条数
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<User> page = userRepository.findAll(pageable);

        return RespVo.success(page,null);
    }



    @RequestMapping("/test001")
    @ResponseBody
    public RespVo getTest(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate now1 = LocalDate.now();

        return RespVo.success(Arrays.asList(now,now1),null);
    }


}
