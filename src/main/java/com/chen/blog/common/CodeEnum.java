package com.chen.blog.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 响应状态信息枚举类
 */
@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {

    FAIL("0000","请求失败"),SUCCESS("0001","请求成功"),
    ABNORMAL("0003","请求异常"),PARAMETER_ERROR("0004","参数错误");


    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
