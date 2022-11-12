package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;
import com.bjpowernode.web.struct.request.UserParam;

/**
 * @author wangjunchen
 */
public interface UserService {
    RCode userRegister(UserParam param);
}
