package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;

/**
 * @author wangjunchen
 */
public interface SmsService {


    /**
     * 验证之前发送的注册短信验证码是否还存在，存在就继续使用
     *
     * @param phone 手机号
     * @return boolean
     */
    boolean checkValidCodeReg(String phone);

    /**
     * 验证之前发送的登录短信验证码是否还存在，存在就继续使用
     *
     * @param phone 手机号
     * @return boolean
     */
    boolean checkValidCodeLogin(String phone);

    /**
     * 发送注册验证码
     *
     * @param phone 手机号
     * @return rCode
     */
    RCode sendSmsReg(String phone);

    /**
     * 发送登录验证码
     *
     * @param phone 手机号
     * @return rCode
     */
    RCode sendSmsLogin(String phone);

    /**
     * 注册功能验证码是否有效
     *
     * @param phone 手机号
     * @param code  验证码
     * @return boolean
     */
    boolean checkCodeReg(String phone, String code);


}
