package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;

/**
 * @author wangjunchen
 */
public interface SmsService {
    RCode sendSmsREG(String phone);
}
