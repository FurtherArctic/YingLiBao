package com.bjpowernode.web.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author wangjunchen
 */
@Configuration
public class SwaggerSettings {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        /* 设置API的基本信息
         * Contact来自springfox.documentation.service.Contact，不要导入错误
         * title：项目标题
         * version：版本号
         * description：基本信息描述
         * contact：联系方式
         */
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("BJ2211盈利宝互联网金融项目")
                .version("1.0")
                .description("前后端分离项目，前端是VUE，html，javaScript，css<br/>" +
                        "后端是SpringBoot，mysql,mybatis-plus")
                .contact(new Contact("BJ2211", "http://www.bjpowernode.com", "1326730110@qq.com"))
                .build();
        //设置docket对象使用apiInfo
        docket = docket.apiInfo(apiInfo);
        //设置参与生成api文档的controller
        docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.bjpowernode.web.controller")).build();
        //禁用api文档，值为false时无法查看api文档，通常项目上线后会禁止
        docket = docket.enable(true);
        return docket;
    }
}
