package com.bjpowernode;

import com.bjpowernode.db.mapper.UserMapper;
import com.bjpowernode.web.struct.config.RealNameConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YlbApplicationTests {
    @Resource
    UserMapper userMapper;
    @Resource
    RealNameConfig realNameConfig;

    @Test
    void contextLoads() {
        System.out.println("================================================================================================");
        System.out.println(realNameConfig.getUrl());
        System.out.println(realNameConfig.getAppkey());
    }

}
