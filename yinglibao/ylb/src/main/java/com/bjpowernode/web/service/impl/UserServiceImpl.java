package com.bjpowernode.web.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.common.RCode;
import com.bjpowernode.db.domain.FinanceAccountDO;
import com.bjpowernode.db.domain.UserDO;
import com.bjpowernode.db.mapper.FinanceAccountMapper;
import com.bjpowernode.db.mapper.UserMapper;
import com.bjpowernode.web.service.UserService;
import com.bjpowernode.web.struct.request.UserParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

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
            String newPassword = DigestUtil.md5Hex(param.getPassword() + salt);
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
}
