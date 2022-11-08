package com.bjpowernode;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 注解@EnableSwagger2启动swagger文档
 * @author wangj
 */
@EnableSwaggerBootstrapUI
@EnableSwagger2
@MapperScan(basePackages = "com.bjpowernode.db.mapper")
@SpringBootApplication
public class YlbApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlbApplication.class, args);
    }

}
