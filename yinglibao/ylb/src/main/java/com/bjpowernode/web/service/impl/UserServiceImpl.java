package com.bjpowernode.web.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.common.RCode;
import com.bjpowernode.common.redis.RedisAssist;
import com.bjpowernode.common.redis.RedisKey;
import com.bjpowernode.db.domain.FinanceAccountDO;
import com.bjpowernode.db.domain.UserDO;
import com.bjpowernode.db.mapper.FinanceAccountMapper;
import com.bjpowernode.db.mapper.UserMapper;
import com.bjpowernode.web.service.UserService;
import com.bjpowernode.web.struct.config.RealNameConfig;
import com.bjpowernode.web.struct.request.UserParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunchen
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${secret.salt}")
    private String salt;
    @Resource
    UserMapper userMapper;
    @Resource
    FinanceAccountMapper financeAccountMapper;
    @Resource
    RedisAssist redisAssist;

    @Resource
    private RealNameConfig realNameConfig;

    /**
     * 注册账号
     *
     * @param param 用户参数对象
     * @return 枚举对象，不同信息表示不同的结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized RCode userRegister(UserParam param) {
        RCode rCode;
        //1.判断手机号是否已经被注册过
        QueryWrapper<UserDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("phone", param.getPhone());
        UserDO userDO = userMapper.selectOne(queryWrapper1);

        if (userDO == null) {
            //2.手机没有注册，可以注册新用户
            //3.密码加盐（自定义的字符串，32位以上）
            String newPassword = DigestUtil.md5Hex(param.getSecret() + salt);
            //4.向数据库u_user表中添加新的user
            userDO = new UserDO();
            userDO.setAddTime(new Date());
            userDO.setLoginPassword(newPassword);
            userDO.setPhone(param.getPhone());
            userMapper.insert(userDO);

            //5.添加新用户的同时进行金融开户，添加account
            FinanceAccountDO financeAccountDO = new FinanceAccountDO();
            financeAccountDO.setAvailableMoney(new BigDecimal("0"));
            //mybatis <selectKey>实现获取主键的功能
            financeAccountDO.setUid(userDO.getId());
            financeAccountMapper.insert(financeAccountDO);

            rCode = RCode.SUCCESS;
        } else {
            rCode = RCode.PHONE_EXIST_ERROR;
        }
        return rCode;
    }

    /**
     * 用户登录操作
     *
     * @param param 前端发送的登录信息，即用户参数对象
     * @return 用户信息
     */
    @Override
    public UserDO userLogin(UserParam param) {
        //1. 处理密码，和注册时一样
        String newPassword = DigestUtil.md5Hex(param.getSecret() + salt);
        //2. 封装组织查询条件
        QueryWrapper<UserDO> qw = new QueryWrapper<>();
        qw.eq("phone", param.getPhone()).eq("login_password", newPassword);
        //3. 查询数据库
        UserDO userDO = userMapper.selectOne(qw);
        //4. 判断对象
        if (userDO != null) {
            //5. 用户有效，更新登录时间
            userDO.setLastLoginTime(new Date());
            userMapper.updateById(userDO);
        }
        //6. 返回userDO对象
        return userDO;
    }

    /**
     * 将token存入redis中
     *
     * @param token     令牌
     * @param redisData 键值对数据，field和value
     * @return boolean，登录成功与否
     */
    @Override
    public boolean saveTokenRedis(String token, Map<String, String> redisData) {
        String key = RedisKey.TOKEN_LOGIN + token.toUpperCase();
        return redisAssist.addHash(key, redisData, 60);

    }

    @Override
    public UserDO queryById(Integer uid) {

        return userMapper.selectById(uid);
    }

    /**
     * @param uid    用户id
     * @param name   用户真实姓名
     * @param idCard 身份证号
     * @return 枚举值
     */
    @Override
    public RCode doRealName(Integer uid, String name, String idCard) {
        RCode rCode = RCode.REALNAME_FAILURE;
        //调用第三方接口
        boolean isok = executeHttpRequest(idCard, name);
        if (isok) {
            //更新数据库
            UserDO user = new UserDO();
            user.setId(uid);
            user.setName(name);
            user.setIdCard(idCard);

            int rows = userMapper.updateById(user);
            if (rows > 0) {
                rCode = RCode.SUCCESS;
            }
        }
        return rCode;
    }

    @Override
    public void saveUidTokenRedis(String token, Integer id) {
        String key = RedisKey.TOKEN_UID + id;
        redisAssist.addString(key, token, 60);
    }

    /**
     * 获取当前id之前登录时生成的已存在的token
     *
     * @param id 用户
     * @return token
     */
    @Override
    public String getTokenForUid(Integer id) {
        String key = RedisKey.TOKEN_UID + id;
        return redisAssist.getString(key);
    }

    /**
     * 私有方法，不允许外部调用
     *
     * @param idCard 身份证号
     * @param name   姓名
     * @return boolean
     */
    private synchronized boolean executeHttpRequest(String idCard, String name) {
        boolean isok = false;
        Map<String, Object> param = new HashMap<>(20);
        param.put("cardNo", idCard);
        param.put("realName", name);
        param.put("appkey", realNameConfig.getAppkey());
        try {

            String response = "{\n" +
                    "    \"code\": \"10000\",\n" +
                    "    \"charge\": false,\n" +
                    "    \"remain\": 1305,\n" +
                    "    \"msg\": \"查询成功\",\n" +
                    "    \"result\": {\n" +
                    "        \"error_code\": 0,\n" +
                    "        \"reason\": \"成功\",\n" +
                    "        \"result\": {\n" +
                    "            \"realname\": \"张三\",\n" +
                    "            \"idcard\": \"350721197702134399\",\n" +
                    "            \"isok\": true\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            if (StrUtil.isNotBlank(response)) {
                JSONObject entity = JSONUtil.parseObj(response);
                if ("10000".equals(entity.getStr("code"))) {
                    JSONObject result = entity.getJSONObject("result").getJSONObject("result");
                    if ("true".equalsIgnoreCase(result.getStr("isok"))) {
                        isok = true;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isok;
    }
}
