package com.chen.blog.repository;

import com.chen.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUsername(String username);

    User findUserByPhone(String phone);

    User findUserByPhoneOrAccount(String phone,String account);

    Page<User> findAllByNicknameLike(String nickname, Pageable pageable);
}
