package com.bjpowernode.web.controller;

import cn.hutool.core.util.PhoneUtil;
import com.bjpowernode.common.RCode;
import com.bjpowernode.web.service.SmsService;
import com.bjpowernode.web.struct.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "注册短信验证码")
    @RequestMapping("/sms/code/reg")
    public CommonResult sendSmsReg(@RequestParam String phone) {
        CommonResult commonResult = CommonResult.failure();
        if (PhoneUtil.isPhone(phone)) {
            RCode rCode = smsService.sendSmsREG(phone);
            commonResult.setRCode(rCode);
        } else {
            //手机号码错误
            commonResult.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return commonResult;
    }
}
