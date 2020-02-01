package com.chen.blog.utils;

import com.chen.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtils {

    private static final Logger log = LoggerFactory.getLogger(SessionUtils.class);


    /***
     * 从session中获取用户信息
     *
     * @return
     */
    public final static User getUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if("anonymousUser".equals(object.toString())) {
            return null;
        }
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (null != user) {
                return user;
            }
        }catch (ClassCastException e) {
            log.error("类型转换错误");
        }
        return null;
    }

    public final static void updateUserInfo(String nickname,String briefIntr,String headurl){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setHeadurl(headurl);
        user.setNickname(nickname);
        user.setBriefIntr(briefIntr);
    }


    /**
     * 获取用户ID，未取到ID则返回一个非法的用户ID，避免保存数据出错
     *
     * @return userId 用户ID
     */
    public final static Long getUserId() {
        User user = getUser();
        return (null == user) ? -1 : user.getId();
    }
}
