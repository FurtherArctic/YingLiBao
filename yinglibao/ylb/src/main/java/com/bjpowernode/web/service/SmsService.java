package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;

/**
 * @author wangjunchen
 */
public interface SmsService {
    RCode sendSmsREG(String phone);
    /**
     * 检查验证码是否有效
     *
     */
    boolean checkValidCodeReg(String phone);

    boolean checkCodeReg(String phone, String code);
}
