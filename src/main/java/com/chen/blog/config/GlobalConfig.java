package com.chen.blog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("direct/login");
        registry.addViewController("/register").setViewName("direct/register");
        registry.addViewController("/home").setViewName("direct/home");
        registry.addViewController("/edit").setViewName("security/edit");
        registry.addViewController("/manage").setViewName("security/manage");
        registry.addViewController("/author").setViewName("direct/author");
    }
}
