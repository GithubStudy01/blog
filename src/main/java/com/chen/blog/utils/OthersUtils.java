package com.chen.blog.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class OthersUtils {


    private static String DATE_PATTERN = "yyyy-MM-dd";

    private static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 创建手机验证码
     *
     * @param digit
     * @return
     */
    public static String createCode(Integer digit){
        String random = Math.random() + "";
        random = random.substring(random.indexOf(".") + 1, random.indexOf(".") + digit + 1);
        return random;
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
//    public static String MD5(String str) {
//        try {
//            byte[] bytes = str.getBytes();
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            str = Hex.encodeHexString(digest.digest(bytes));
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return str;
//    }

    /**
     * 生成taken
     *
     * 4fb22316c04a4e6baf7f364e5131215f
     *
     * @return
     */
    public static String createCodeToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 得到当天剩余秒数
     *
     * @return
     */
    public static long getCurrentDateRemainSeconds() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
        return ChronoUnit.SECONDS.between(now, tomorrow);
    }


    /**
     * 得到创建时间
     * @return
     */
    public static LocalDateTime getCreateTime(){
        return LocalDateTime.now();
    }

    /**
     * 得到日期
     * @return
     */
    public static LocalDate getBirthday(){
        return LocalDate.now();
    }





    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME_PATTERN);
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }



}
