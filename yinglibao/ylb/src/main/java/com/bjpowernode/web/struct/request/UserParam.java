package com.bjpowernode.web.struct.request;

import cn.hutool.core.util.PhoneUtil;
import lombok.Data;

/**
 * 注册和登录参数类
 *
 * @author wangjunchen
 */
@Data
public class UserParam {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String secret;
    /**
     * 验证码
     */
    private String code;

    /**
     * 检查注册参数是否符合要求
     * 1.手机号格式正确
     * 2.密码不为空，且为32位MD5值
     * 验证码不为空且为4位数字
     *
     * @return boolean
     */
    public boolean checkData(int len) {
        return PhoneUtil.isPhone(phone) && (secret != null && secret.length() == 32) && (code != null && code.length() == len);
    }
}
