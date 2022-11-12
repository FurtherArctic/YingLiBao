package com.bjpowernode.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 用户财务资金账户表
 * @TableName u_finance_account
 */
@TableName(value ="u_finance_account")
@Data
public class FinanceAccountDO implements Serializable {
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
     * 用户可用资金
     */
    @TableField(value = "available_money")
    private BigDecimal availableMoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}