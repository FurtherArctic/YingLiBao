package com.bjpowernode.web.struct.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangjunchen
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {
    private String url;
    private String appkey;
    private String regText;
    private String loginText;
}
