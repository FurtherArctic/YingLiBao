package com.bjpowernode.common;

/**
 * @author wangjunchen
 */
public enum RCode {
    //请求成功
    SUCCESS(1, "请求成功"),
    //请求失败
    FAILURE(-1, "请求失败"),
    //请求参数错误
    REQUEST_PARAM_ERROR(-2, "参数错误"),
    //手机号格式不正确
    PHONE_FORMAT_ERROR(-1001, "手机号格式不正确"),
    //验证码发送失败
    SMS_SEND_ERROR(-1002, "验证码发送失败"),
    //验证码已存在且依然可用
    SMS_CODE_USEFUL(-1003, "验证码可以继续使用，不用重新发送"),
    //手机号已经被注册过
    PHONE_EXIST_ERROR(-1004, "手机号已注册"),
    //验证码无效，需要重新获取
    SMS_CODE_INVALID(-1005, "验证码无效"),
    //登录信息无效，通常是用户名或者密码不正确
    USER_NAME_PASSWORD_INVALID(-1006, "登录信息无效"),
    FAIL_TOKEN_INVALID(2000, "token无效");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
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

    @SuppressWarnings("unused")
    public void setText(String text) {
        this.text = text;
    }

    RCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }
}
