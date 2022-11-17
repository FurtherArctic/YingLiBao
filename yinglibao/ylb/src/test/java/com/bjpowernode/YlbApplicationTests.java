package com.bjpowernode;

import com.bjpowernode.db.domain.UserDO;
import com.bjpowernode.db.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YlbApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        UserDO userDO1 = userMapper.selectById(38);

        UserDO userDO2 = userMapper.selectById(37);

        UserDO userDO3 = userMapper.selectById(39);

        UserDO userDO4 = userMapper.selectById(40);
        System.out.println("================================================================================================");
        System.out.println("userDO1" + userDO1);
        System.out.println("userDO2" + userDO2);
        System.out.println("userDO3" + userDO3);
        System.out.println("userDO4" + userDO4);
    }

}
