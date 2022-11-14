package com.bjpowernode.web.service;

import com.bjpowernode.common.RCode;
import com.bjpowernode.db.domain.UserDO;
import com.bjpowernode.web.struct.request.UserParam;

import java.util.Map;

/**
 * @author wangjunchen
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param param 用户参数对象
     * @return 枚举值
     */
    RCode userRegister(UserParam param);

    /**
     * 用户登录验证
     *
     * @param param 前端发送的登录信息，即用户参数对象
     * @return 用户信息
     */
    UserDO userLogin(UserParam param);

    /**
     * 将token存储到redis中
     *
     * @param token     令牌
     * @param redisData 键值对数据，field和value
     * @return boolean
     */
    boolean saveTokenRedis(String token, Map<String, String> redisData);
}
