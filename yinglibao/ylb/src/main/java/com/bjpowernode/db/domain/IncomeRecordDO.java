package com.bjpowernode.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 收益记录表
 * @TableName b_income_record
 */
@TableName(value ="b_income_record")
@Data
public class IncomeRecordDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 产品ID
     */
    @TableField(value = "prod_id")
    private Integer prodId;

    /**
     * 投标记录ID
     */
    @TableField(value = "bid_id")
    private Integer bidId;

    /**
     * 投资金额
     */
    @TableField(value = "bid_money")
    private BigDecimal bidMoney;

    /**
     * 期到时间
     */
    @TableField(value = "income_date")
    private Date incomeDate;

    /**
     * 收益金额
     */
    @TableField(value = "income_money")
    private BigDecimal incomeMoney;

    /**
     * 收益状态（0未返，1已返）
     */
    @TableField(value = "income_status")
    private Integer incomeStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}