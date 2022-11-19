package com.bjpowernode.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.bjpowernode.common.RCode;
import com.bjpowernode.db.domain.UserDO;
import com.bjpowernode.web.service.SmsService;
import com.bjpowernode.web.service.UserService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.request.RealNameParam;
import com.bjpowernode.web.struct.request.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * 用户注册，提供的
     * {"phone":"13XXX","secret":"pass","code":"1234"}
     *
     * @param param 用户参数
     * @return commonResult
     */
    @ApiOperation(value = "用户注册", notes = "使用手机号注册新用户")
    @PostMapping("/user/register")
    public CommonResult userRegister(@RequestBody UserParam param) {
        //创建默认值
        CommonResult commonResult = CommonResult.failure(RCode.REQUEST_PARAM_ERROR);
        //1.检查参数格式，是否符合要求
        boolean b = param.checkData(4);
        if (b) {
            //2.检查短信验证码是否有效（与redis中存储的验证码作比较）
            boolean checkCodeReg = smsService.checkCodeReg(param.getPhone(), param.getCode());
            if (checkCodeReg) {
                RCode rcode = userService.userRegister(param);
                if (rcode == RCode.SUCCESS) {
                    commonResult = CommonResult.success("注册成功");
                }
            } else {
                //验证码无效
                commonResult.setRCode(RCode.SMS_CODE_INVALID);
            }
        }
        return commonResult;
    }

    /**
     * 用户登录，前端提供手机号，验证码，密码，去数据库以及redis查询相关数据是否匹配
     * 非首次登录需要验证token，首次验证需要生成token，用户相关操作都要使用token验证
     *
     * @param userParam 用户信息
     * @return commonResult
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @ApiOperation(value = "用户登录", notes = "通过手机号，验证码和密码登录账号")
    @PostMapping("/user/login")
    public CommonResult userLogin(@RequestBody UserParam userParam) {
        //默认结果，参数检查失败
        CommonResult commonResult = CommonResult.failure(RCode.REQUEST_PARAM_ERROR);
        if (userParam.checkData(6)) {
            //执行登录
            //1.验证短信验证码
            boolean check = smsService.checkCodeLogin(userParam.getPhone(), userParam.getCode());
            if (check) {
                //2. 验证用户信息是否有效，通过前端返回的手机号，密码等信息去数据库查询匹配
                UserDO userDO = userService.userLogin(userParam);
                //3. 判断登陆结果
                if (userDO != null) {
                    //登录成功，生成token，此处采用UUID生成token
                    String token;
                    boolean saved;
                    //先从redis中获取Token
                    String savedToken = userService.getTokenForUid(userDO.getId());
                    //如果redis中已经有token则使用已有token，没有则新建并存储
                    if (StrUtil.isNotBlank(savedToken)) {
                        token = savedToken;
                        saved = true;
                    } else {
                        token = IdUtil.simpleUUID();
                        //4. 存储token-redis,使用hash类型，有效时间1小时
                        Map<String, String> redisData = new HashMap<>(10);
                        redisData.put("uid", String.valueOf(userDO.getId()));
                        redisData.put("name", userDO.getName());
                        redisData.put("loginTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        saved = userService.saveTokenRedis(token, redisData);
                    }
                    if (saved) {
                        //把登录的UID和token使用String类型存储起来
                        userService.saveUidTokenRedis(token.toUpperCase(), userDO.getId());
                        //5. 返回token给请求方，以及userid，name，phone等数据
                        Map<String, String> responseData = new HashMap<>(10);
                        responseData.put("uid", String.valueOf(userDO.getId()));
                        responseData.put("phone", userDO.getPhone());
                        responseData.put("name", userDO.getName());
                        responseData.put("token", token);
                        //请求成功返回数据
                        commonResult = CommonResult.success(responseData);
                    }
                } else {
                    //登陆失败，用户名或者密码错误
                    commonResult.setRCode(RCode.USER_NAME_PASSWORD_INVALID);
                }
            } else {
                //验证码无效
                commonResult.setRCode(RCode.SMS_CODE_INVALID);
            }
        }
        return commonResult;
    }

    /**
     * 实名认证
     *
     * @param realNameParam 实名信息
     * @param uid           用户id
     * @return commonResult
     */
    @ApiOperation(value = "实名认证")
    @PostMapping("/user/realname")
    public CommonResult userRealName(@RequestBody RealNameParam realNameParam, @RequestHeader(value = "uid") Integer uid) {
        CommonResult commonResult = CommonResult.failure();
        if (realNameParam.checkData() && uid > 0) {
            //1.检查是否做过实名认证
            UserDO userDO = userService.queryById(uid);
            //判断用户是否存在
            if (userDO != null) {
                //判断用户姓名是否为空，为空表示没有实名认证
                if (StrUtil.isBlank(userDO.getName())) {
                    //需要实名认证
                    RCode rCode = userService.doRealName(uid, realNameParam.getName(), realNameParam.getIdCard());
                    commonResult.setRCode(rCode);
                } else {
                    //不需要实名认证
                    commonResult.setRCode(RCode.REALNAME_EXIST);
                }
            }
        } else {
            commonResult.setRCode(RCode.REQUEST_PARAM_ERROR);
        }
        return commonResult;
    }

}
