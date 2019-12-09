package com.chen.blog;

import com.chen.blog.entity.Blog;
import com.chen.blog.entity.TestUser;
import com.chen.blog.entity.User;
import com.chen.blog.repository.TestUserRepository;
import com.chen.blog.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private TestUserRepository testUserRepository;


    @Test
    public void test01(){
        final TestUser testUser = new TestUser(null, "lao", "123456", "广东揭阳");
        testUserRepository.save(testUser);


    }

    @Test
    public void test02(){
        final List<TestUser> list = testUserRepository.getAllByUsernameLike("%lao%");
        for (TestUser testUser : list) {
            System.out.println(testUser);
        }

    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test03(){
        //final User user = userRepository.getOne(1L);//非懒加载字段也有可能出现异常
        final User user = userRepository.findById(1L).get();

        String password = user.getPassword();
//        String username = user.getUsername();
//        System.out.println(username);
        System.out.println(password);

        System.out.println(user);

        Blog blog = user.getBlog();
        System.out.println(blog.getBlogName());

//        final Optional<User> optionalUser = userRepository.findById(2L);
//        System.out.println(optionalUser.isPresent());


    }


}
