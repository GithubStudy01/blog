package com.chen.blog.repository;

import com.chen.blog.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestUserRepository extends JpaRepository<TestUser,Integer> {


    List<TestUser> getAllByUsernameLike(String username);


}
