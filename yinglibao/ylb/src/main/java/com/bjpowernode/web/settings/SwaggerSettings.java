package com.bjpowernode.web.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author wangjunchen
 */
@Configuration
public class SwaggerSettings {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //设置API的基本信息
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("BJ2211盈利宝互联网金融项目")
                .version("1.0")
                .description("前后端分离项目，前端是VUE，html，javaScript，css<br/>" +
                        "后端是SpringBoot，")
                .contact(new Contact("BJ2211", "", ""))
                .build();
        //设置docket对象使用apiInfo
        docket = docket.apiInfo(apiInfo);
        //设置参与生成api文档的controller
        docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.bjpowernode.web.controller")).build();
        //禁用api文档
        //docket = docket.enable(false);
        return docket;
    }
}
