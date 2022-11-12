package com.bjpowernode.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表
 * @author wangjunchen
 * @TableName b_recharge_record
 */
@TableName(value ="b_recharge_record")
@Data
public class RechargeRecordDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 充值订单号
     */
    @TableField(value = "recharge_no")
    private String rechargeNo;

    /**
     * 充值订单状态（0充值中，1充值成功，2充值失败）
     */
    @TableField(value = "recharge_status")
    private Integer rechargeStatus;

    /**
     * 充值金额
     */
    @TableField(value = "recharge_money")
    private BigDecimal rechargeMoney;

    /**
     * 充值时间
     */
    @TableField(value = "recharge_time")
    private Date rechargeTime;

    /**
     * 充值描述
     */
    @TableField(value = "recharge_desc")
    private String rechargeDesc;

    /**
     * 充值渠道
     */
    @TableField(value = "channel")
    private String channel;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}