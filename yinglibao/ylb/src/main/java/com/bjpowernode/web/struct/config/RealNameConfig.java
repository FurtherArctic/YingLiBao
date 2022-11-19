package com.bjpowernode.web.struct.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangjunchen
 */
@Component
@ConfigurationProperties(prefix = "realname")
@Data
public class RealNameConfig {
    private String url;
    private String appkey;
}
