package com.chen.blog.service;

import com.chen.blog.entity.User;
import com.chen.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void register(User user) {


    }
}
