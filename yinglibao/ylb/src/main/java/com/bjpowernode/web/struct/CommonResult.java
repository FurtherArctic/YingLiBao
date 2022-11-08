package com.bjpowernode.web.struct;

import com.bjpowernode.common.RCode;
import lombok.Data;

/**
 * 通用应答结果类，专门用于给前端返回数据
 *
 * @author wangjunchen
 */
@Data
public class CommonResult {
    /**
     * 应答码
     */
    private Integer code;
    /**
     * 应答消息
     */
    private String msg;
    /**
     * 详细信息
     */
    private Object info;

    public static CommonResult success(Object info) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(RCode.SUCCESS.getCode());
        commonResult.setMsg(RCode.SUCCESS.getText());
        commonResult.setInfo(info);
        return commonResult;
    }

    public static CommonResult failure() {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(RCode.FAILURE.getCode());
        commonResult.setMsg(RCode.FAILURE.getText());
        commonResult.setInfo("");
        return commonResult;
    }
}
