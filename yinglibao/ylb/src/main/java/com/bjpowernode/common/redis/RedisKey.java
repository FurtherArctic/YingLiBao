package com.bjpowernode.common.redis;

/**
 * Redis数据的key统一存放
 *
 * @author wangjunchen
 */
public class RedisKey {
    /**
     * 投资排行榜
     */
    public static final String BID_MONEY_RANK = "BID:MONEY:RANK";
    /**
     * 注册验证码
     */
    public static final String SMS_CODE_REG = "SMS:REG:";
    /**
     * 登录验证码
     */
    public static final String SMS_CODE_LOGIN = "SMS:LOGIN:";

    /**
     * 登录令牌token
     */
    public static final String TOKEN_LOGIN = "TOKEN:LOGIN:";
}
