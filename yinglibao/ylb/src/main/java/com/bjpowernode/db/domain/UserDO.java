package com.bjpowernode.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author wangjunchen
 * @TableName u_user
 */
@TableName(value ="u_user")
@Data
public class UserDO implements Serializable {
    /**
     * 用户ID，主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 注册手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 登录密码，密码长度最大16位
     */
    @TableField(value = "login_password")
    private String loginPassword;

    /**
     * 用户姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户身份证号码
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 注册时间
     */
    @TableField(value = "add_time")
    private Date addTime;

    /**
     * 最近登录时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 用户头像文件路径
     */
    @TableField(value = "header_image")
    private String headerImage;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}