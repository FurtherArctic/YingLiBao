package com.bjpowernode.web.struct.request;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @author wangjunchen
 */
@Data
public class RealNameParam {
    private String phone;
    private String name;
    private String idCard;
    public boolean checkData(){
        if(PhoneUtil.isPhone(phone)&& (StrUtil.isNotBlank(name)&&name.length()>=2)&& IdcardUtil.isValidCard(idCard)){
            return true;
        }
        return false;
    }
}
