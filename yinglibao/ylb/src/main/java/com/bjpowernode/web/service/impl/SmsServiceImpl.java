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

    @Override
    public RCode sendSmsREG(String phone) {

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
        String response = "{\n" +
                "    \"code\": \"10000\",\n" +
                "    \"charge\": false,\n" +
                "    \"remain\": 1305,\n" +
                "    \"msg\": \"查询成功\",\n" +
                "    \"result\": {\n" +
                "        \"ReturnStatus\": \"Success\",\n" +
                "        \"Message\": \"ok\",\n" +
                "        \"RemainPoint\": 420842,\n" +
                "        \"TaskID\": 18424321,\n" +
                "        \"SuccessCounts\": 1\n" +
                "    }\n" +
                "}";
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
    public boolean checkValidCodeReg(String phone) {
        String key = RedisKey.SMS_CODE_REG + phone;
        return redisAssist.exists(key);
    }

    @Override
    public boolean checkCodeReg(String phone, String code) {
        String key = RedisKey.SMS_CODE_REG + phone;
        if (redisAssist.exists(key)) {
            String saveCode = redisAssist.getString(key);
            if (code.equals(saveCode)) {
                return true;
            }
        }
        return false;
    }
}
