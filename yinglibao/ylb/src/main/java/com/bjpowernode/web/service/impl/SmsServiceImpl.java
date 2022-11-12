package com.bjpowernode.web.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bjpowernode.common.RCode;
import com.bjpowernode.common.redis.RedisAssist;
import com.bjpowernode.common.redis.RedisKey;
import com.bjpowernode.web.service.SmsService;
import com.bjpowernode.web.struct.config.SmsConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author wangjunchen
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Resource
    private SmsConfig smsConfig;
    @Resource
    RedisAssist redisAssist;

    /**
     * 验证之前发送的注册短信验证码是否还存在，存在就继续使用
     * 将手机号作为key，去redis中查询对应的value，没有过期则会查询到相应的value
     *
     * @param phone 注册手机号
     * @return boolean
     */
    @Override
    public boolean checkValidCodeReg(String phone) {
        String key = RedisKey.SMS_CODE_REG + phone;
        boolean result = redisAssist.exists(key);
        return result;
    }

    /**
     * 验证之前发送的登录短信验证码是否还存在，存在就继续使用
     * 将手机号作为key，去redis中查询对应的value，没有过期则会查询到相应的value
     *
     * @param phone 登录手机号
     * @return boolean
     */
    @Override
    public boolean checkValidCodeLogin(String phone) {
        String key = RedisKey.SMS_CODE_LOGIN + phone;
        boolean result = redisAssist.exists(key);
        return result;
    }

    /**
     * 发送注册验证码，生成四位随机数，发送到用户注册时使用的手机号
     * 同时将该手机号作为key，四位随机数的验证码作为value存入到redis数据库中
     * 以用于用户注册时匹配验证
     *
     * @param phone 手机号
     * @return Rcode
     */
    @Override
    public RCode sendSmsReg(String phone) {
        //默认发送失败
        RCode rCode = RCode.SMS_SEND_ERROR;
        //1.生成4位随机数
        String code = RandomUtil.randomNumbers(4);
        //2.短信内容
        String template = String.format(smsConfig.getRegText(), code);
        //3.使用hutool工具类中的HttpUtil发送请求
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", phone);
        paramMap.put("content", template);
        paramMap.put("appkey", smsConfig.getAppkey());
        //发出去，获取发送结果的json
        String response = "{\n" + "    \"code\": \"10000\",\n" + "    \"charge\": false,\n" + "    \"remain\": 1305,\n" + "    \"msg\": \"查询成功\",\n" + "    \"result\": {\n" + "        \"ReturnStatus\": \"Success\",\n" + "        \"Message\": \"ok\",\n" + "        \"RemainPoint\": 420842,\n" + "        \"TaskID\": 18424321,\n" + "        \"SuccessCounts\": 1\n" + "    }\n" + "}";
        if (StrUtil.isNotBlank(response)) {
            //5.解析json
            JSONObject object = JSONUtil.parseObj(response);
            if ("10000".equals(object.getStr("code"))) {
                //获取ReturnStatus
                String returnStatus = object.getJSONObject("result").getStr("ReturnStatus");
                if ("Success".equals(returnStatus)) {
                    //6.存储短信验证码到redis，设置三分钟有效
                    String key = RedisKey.SMS_CODE_REG + phone;
                    if (redisAssist.addString(key, code, 3)) {
                        rCode = RCode.SUCCESS;
                    }
                }
            }
        }
        System.out.println("注册验证码：" + code);
        return rCode;
    }


    @Override
    public RCode sendSmsLogin(String phone) {
        RCode rCode = RCode.SMS_SEND_ERROR;
        //1.短信验证码6位数字
        String code = RandomUtil.randomNumbers(6);
        //2.生成短信的内容
        String template = String.format(smsConfig.getLoginText(), code);
        //3.调用第三方接口
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", phone);
        paramMap.put("content", template);
        paramMap.put("appkey", smsConfig.getAppkey());

        //发出去，获取发送结果的json
        String response = "{\n" + "    \"code\": \"10000\",\n" + "    \"charge\": false,\n" + "    \"remain\": 1305,\n" + "    \"msg\": \"查询成功\",\n" + "    \"result\": {\n" + "        \"ReturnStatus\": \"Success\",\n" + "        \"Message\": \"ok\",\n" + "        \"RemainPoint\": 420842,\n" + "        \"TaskID\": 18424321,\n" + "        \"SuccessCounts\": 1\n" + "    }\n" + "}";
        if (StrUtil.isNotBlank(response)) {
            //5.解析json
            JSONObject object = JSONUtil.parseObj(response);
            if ("10000".equals(object.getStr("code"))) {
                //获取ReturnStatus
                String returnStatus = object.getJSONObject("result").getStr("ReturnStatus");
                if ("Success".equals(returnStatus)) {
                    //6.存储短信验证码到redis，设置10分钟有效
                    String key = RedisKey.SMS_CODE_LOGIN + phone;
                    if (redisAssist.addString(key, code, 10)) {
                        rCode = RCode.SUCCESS;
                    }
                }
            }
        }
        System.out.println("登陆的短信验证码是" + code);
        return rCode;
    }

    /**
     * 验证注册时用户提供的验证码与系统发送的验证码是否匹配
     *
     * @param phone 手机号
     * @param code  验证码
     * @return boolean
     */
    @Override
    public boolean checkCodeReg(String phone, String code) {
        //通过手机号获取key
        String key = RedisKey.SMS_CODE_REG + phone;
        //判断redis中是否存在相应的key
        if (redisAssist.exists(key)) {
            //key若是存在，则获取该key对应的value值
            String saveCode = redisAssist.getString(key);
            //redis中的value值和用户提供的value值是否匹配
            if (code.equals(saveCode)) {
                //匹配返回true，表示用户使用的验证码有效
                return true;
            }
        }
        //否则验证码无效，返回false
        return false;
    }
}
