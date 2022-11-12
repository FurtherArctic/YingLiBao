package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;
import com.bjpowernode.web.struct.request.UserParam;

/**
 * @author wangjunchen
 */
public interface UserService {
    /**
     * 用户注册
     * @param param 用户参数对象
     * @return 枚举值
     */
    RCode userRegister(UserParam param);
}
