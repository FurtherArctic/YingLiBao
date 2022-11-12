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
 * 投资记录表
 * @TableName b_bid_info
 */
@TableName(value ="b_bid_info")
@Data
public class BidInfoDO implements Serializable {
    /**
     * 投标记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品ID
     */
    @TableField(value = "prod_id")
    private Integer prodId;

    /**
     * 用户ID
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 投标金额
     */
    @TableField(value = "bid_money")
    private BigDecimal bidMoney;

    /**
     * 投标时间
     */
    @TableField(value = "bid_time")
    private Date bidTime;

    /**
     * 投标状态
     */
    @TableField(value = "bid_status")
    private Integer bidStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}