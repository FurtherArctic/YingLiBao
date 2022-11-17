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
 * token拦截器，验证token
 *
 * @author wangjunchen
 */

public class TokenInterceptor implements HandlerInterceptor {

    private final RedisAssist redisAssist;

    /**
     * 此类并未被容器接管，因此如果使用注解注入，会提示为null值，会出现空指针异常，因此需要使用注解注入
     *
     * @param redisAssist redisAssist工具类对象
     */
    public TokenInterceptor(RedisAssist redisAssist) {
        this.redisAssist = redisAssist;
    }

    /**
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return boolean
     * @throws Exception exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String options = "OPTIONS";
        String bearer = "Bearer";
        //1. 处理options请求
        if (options.equals(request.getMethod())) {
            return true;
        }
        boolean checkToken = false;

        /*
         * 从请求头header中取出token，格式为：Authorization：Bearer ba40616cd33f4555b44c7f5830ca6b72
         * 验证token是否
         */
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(authorization) && authorization.contains(bearer)) {
            //获取token值字符串
            String token = authorization.substring(7);
            if (StrUtil.isNotBlank(token)) {
                //比较token与redis 中存储的数据
                String key = RedisKey.TOKEN_LOGIN + token.toUpperCase();
                if (redisAssist.exists(key)) {
                    //比较请求中的uid与redis中存储的uid是否一致
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
            //获取JSON字符串
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
