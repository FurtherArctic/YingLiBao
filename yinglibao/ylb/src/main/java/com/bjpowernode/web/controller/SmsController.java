package com.bjpowernode.web.controller;

import cn.hutool.core.util.PhoneUtil;
import com.bjpowernode.common.RCode;
import com.bjpowernode.web.service.SmsService;
import com.bjpowernode.web.struct.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangjunchen
 */
@Api(tags = "短信功能接口")
@RestController
public class SmsController {
    @Resource
    private SmsService smsService;

    /**
     * 发送注册短信验证码
     *
     * @param phone 手机号
     * @return commonResult封装的验证码
     */
    @ApiOperation(value = "注册短信验证码", notes = "用户点击获取验证码后，系统会验证手机号格式，然后再查询redis中相应的手机号是否有合法的验证码，若手机号格式正确且redis中没有相关数据，则会通过第三方公司向用户的手机号上发送统一格式的随机验证码。否则会给出相关的提示")
    @RequestMapping("/sms/code/reg")
    public CommonResult sendSmsReg(@RequestParam String phone) {
        CommonResult commonResult = CommonResult.failure();
        if (PhoneUtil.isPhone(phone)) {
            //如果验证码有效，继续使用，不重新发送
            if (smsService.checkValidCodeReg(phone)) {
                commonResult.setRCode(RCode.SMS_CODE_USEFUL);
                return commonResult;
            }
            //发送短信
            RCode rCode = smsService.sendSmsReg(phone);
            commonResult.setRCode(rCode);
        } else {
            //手机号码错误
            commonResult.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return commonResult;
    }

    @ApiOperation(value = "登录短信验证码", notes = "功能和实现方式与注册验证码的获取方式和逻辑相似，不再赘述")
    @GetMapping("/sms/code/login")
    public CommonResult sendSmsLogin(@RequestParam String phone) {
        CommonResult commonResult = CommonResult.failure();
        if (PhoneUtil.isPhone(phone)) {
            //1.验证码可以继续使用
            if (smsService.checkValidCodeLogin(phone)) {
                commonResult.setRCode(RCode.SMS_CODE_USEFUL);
                return commonResult;
            }
            //2.调用发送短信接口
            RCode rCode = smsService.sendSmsLogin(phone);
            commonResult.setRCode(rCode);
        }
        return commonResult;
    }
}
