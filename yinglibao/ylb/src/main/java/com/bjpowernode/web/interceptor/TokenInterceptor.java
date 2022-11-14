package com.bjpowernode.web.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bjpowernode.common.RCode;
import com.bjpowernode.common.redis.RedisAssist;
import com.bjpowernode.common.redis.RedisKey;
import com.bjpowernode.web.struct.CommonResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author wangjunchen
 */

public class TokenInterceptor implements HandlerInterceptor {

    private final RedisAssist redisAssist;

    public TokenInterceptor(RedisAssist redisAssist) {
        this.redisAssist = redisAssist;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String options = "OPTIONS";
        String bearer = "Bearer";
        if (options.equals(request.getMethod())) {
            return true;
        }
        boolean checkToken = false;
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(authorization) && authorization.contains(bearer)) {
            String token = authorization.substring(7);
            if (StrUtil.isNotBlank(token)) {
                String key = RedisKey.TOKEN_LOGIN + token.toUpperCase();
                if (redisAssist.exists(key)) {
                    String headerUid = request.getHeader("uid");
                    String fieldUid = redisAssist.getFieldHash(key, "uid");
                    if (StrUtil.isNotBlank(headerUid)) {
                        checkToken = headerUid.equals(fieldUid);
                    }
                }
            }
        }
        //检查token的结果
        if (!checkToken) {
            //比较失败，返回一个反馈信息，告诉前端，需要重新登录
            //创建一个CommonResult，作为自定义的
            CommonResult commonResult = CommonResult.failure(RCode.FAIL_TOKEN_INVALID);
            String json = JSONUtil.toJsonStr(commonResult);
            //输出JSON
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(json);
            printWriter.flush();
            printWriter.close();
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
