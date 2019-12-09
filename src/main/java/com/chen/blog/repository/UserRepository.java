package com.chen.blog.repository;

import com.chen.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUsername(String username);

    User findUserByPhone(String phone);

}
