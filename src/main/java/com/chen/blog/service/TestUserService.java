package com.chen.blog.service;

import com.chen.blog.repository.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUserService {

    @Autowired
    private TestUserRepository testUserRepository;


}
