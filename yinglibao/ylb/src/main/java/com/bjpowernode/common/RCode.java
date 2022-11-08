package com.bjpowernode.common;

import lombok.Data;

/**
 * @author wangjunchen
 */
public enum RCode {
    SUCCESS(1,"请求成功"),
    FAILURE(-1,"请求失败")
    ;
    /**
     *
     */
    private Integer code;
    /**
     *
     */
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    RCode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }
}
