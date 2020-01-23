package com.chen.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 自定义配置类
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//启动方法级别的权限控制
//public class SecurityConfigSave extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    UserDetailService userDetailService;
//    @Autowired
//    BlogSecurityProvider blogSecurityProvider;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/","/css/**","/user/checkPhoneUnique","/user/checkPhoneUnique","/user/checkPhoneUnique","/js/**","/images/**","/iconfont/**","/index","/search/**","/home","/details")//允许所有用户访问  /main 多个路径用 , 隔开,如："/test1","/test2","/test3"
////                .permitAll()
////                .anyRequest().authenticated();
//        http.authorizeRequests()
//                .antMatchers("/article/changeOverhead","/article/changeType","/article/delete/**","/edit","/manage","/comment/reply").authenticated()//任何经过身份验证的用户才能访问
//                .and()
//                .logout()
//                .logoutSuccessUrl("/logoreg")//退出成功跳转的地址
//                .deleteCookies()
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable();        //暂时禁用CSRF，否则无法提交表单
//
//        http.authorizeRequests()
//                .and()
//                .formLogin()
//                .loginPage("/logoreg")    //跳转登录页面的控制器，该地址要保证和表单提交的地址一致！
//                .defaultSuccessUrl("/index", true)//登录成功默认跳转的页面 true表示登录成功后始终从定向到 "/index"
//                .failureUrl("/logoreg?error=true") //发生错误跳转的地址
//                .permitAll()
//                .and()
//                .headers().frameOptions().disable()//关闭 防止网页被其他框架使用
//                .and().exceptionHandling().accessDeniedPage("/403");//拒绝访问时跳转
//		/*.and()
//        .sessionManagement()
//        .maximumSessions(1)
//        .maxSessionsPreventsLogin(true)
//        .expiredUrl("/exceeded");	*/
//        //启用rememberMe功能，将用户信息保存在cookie中
//        http.rememberMe()
//                .rememberMeParameter("remberme");
//    }
//
//    @Override
//    protected UserDetailsService userDetailsService() {
//        //自定义用户权限信息类
//        return userDetailService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //自定义用户登录校验类 (认证)
//        auth.authenticationProvider(blogSecurityProvider);
//    }
//}
