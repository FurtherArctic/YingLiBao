package com.bjpowernode.web.struct.request;

import cn.hutool.core.util.PhoneUtil;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author wangjunchen
 */
@Data
public class UserParam {
    private String phone;
    private String secret;
    private String code;

    public boolean checkData() {
        if (PhoneUtil.isPhone(phone) && (secret != null && secret.length() == 32) && (code != null && code.length() == 4)) {
            return true;
        } else {
            return false;
        }
    }
}
