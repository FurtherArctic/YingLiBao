package com.bjpowernode.common;

import lombok.Data;

/**
 * @author wangjunchen
 */
public enum RCode {
    SUCCESS(1, "请求成功"),
    FAILURE(-1, "请求失败"),
    PHONE_FORMAT_ERROR(-1001, "手机号格式不正确"),
    REQUEST_PARAM_ERROR(1005, "参数无效"),

    SMS_SEND_ERROR(-1002, "验证码发送失败"),
    SMS_CODE_USE(1004, "验证码可以继续使用，不用重新发送"),
    PHONE_EXIST_ERROR(-100,"手机号已注册"),
    SMS_CODE_INVALID(-1002, "验证码无效");
    /**
     *
     */
    private Integer code;
    /**
     *
     */
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    RCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }
}
