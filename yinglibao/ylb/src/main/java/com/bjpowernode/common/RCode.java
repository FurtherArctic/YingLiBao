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
    //不能重复认证
    REALNAME_EXIST(1006, "不能重复认证"),
    //实名认证失败
    REALNAME_FAILURE(1006, "实名认证失败"),
    ACCOUNT_NOT_EXITS(1110, "资金账号不存在"),
    ACCOUNT_MONEY_NOT_ENOUGH(1111, "资金余额不足"),
    PRODUCT_NOT_EXITS(1112, "理财产品不存在"),
    PRODUCT_NOT_SELL(1113, "理财产品不可售卖"),
    PRODUCT_LEFT_MONEY(1114, "剩余金额不足"),
    INVEST_MONEY_RANGE(1115, "投资金额不符合要求"),
    //token无效，需要重新登录
    FAIL_TOKEN_INVALID(-1007, "请重新登录");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态信息
     */
    private final String text;

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    RCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }
}
