package com.bjpowernode.web.controller;

import com.bjpowernode.common.RCode;
import com.bjpowernode.web.service.SmsService;
import com.bjpowernode.web.service.UserService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.request.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangjunchen
 */
@Api(tags = "用户功能模块")
@RestController
public class UserController {
    @Resource
    private SmsService smsService;

    @Resource
    UserService userService;

    /**
     * 用户注册
     * {"phone":"13XXX","secret":"pass","code":"1234"}
     *
     * @param param
     * @return commonResult
     */
    @ApiOperation(value = "用户注册", notes = "使用手机号注册新用户")
    @PostMapping("/user/register")
    public CommonResult userRegister(@RequestBody UserParam param) {
        //创建默认值
        CommonResult commonResult = CommonResult.failure(RCode.REQUEST_PARAM_ERROR);
        //1.检查参数
        if (param.checkData()) {
            //2.检查短信验证码是否有效
            boolean checkCodeReg = smsService.checkCodeReg(param.getPhone(), param.getCode());
            if (checkCodeReg) {
                RCode rcode = userService.userRegister(param);
                commonResult.setRCode(rcode);

            } else {
                //验证码无效
                commonResult.setRCode(RCode.SMS_CODE_INVALID);
            }
        }

        return commonResult;
    }
}
