package com.chen.blog.common;

/**
 * 字典
 */
public class WordDefined {

    /**
     * 密码盐
     */
    public static final String PSWD_SALT = "cycblog";

    public static final String PHONE_NOT_UNIQUE = "手机号码已经被使用！";


    public static final String PHONE_SEND_CODE_PASS_MAX_COUNT = "该手机号码一天发送验证码次数超过5次！";



    //手机验证码位数
    public static final Integer PHOME_CODE_DIGIT = 6;

    //	redis-key
    //手机验证码前缀
    public static final String BLOG_CODE_PHONE="blog:code:phone:";//blog:code:phone:1355xxxxx
    //手机验证码次数
    public static final String BLOG_CODE_PHONE_COUNT="blog:code:phone:count:";//blog:code:phone:count:1355xxxxx
    //验证码通过后token
    public static final String BLOG_TOKEN_CODE="blog:token:code:";//blog:token:code:1355xxxxx

    //手机验证码过期时间
    public static final Integer PHONE_CODE_TIME_OUT = 60 * 2;

    public static final Integer MAX_PHONE_SEND_COUNT = 5;

}
