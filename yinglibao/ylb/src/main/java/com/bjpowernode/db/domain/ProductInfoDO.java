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
 * 产品信息表
 * @author wangj
 * @TableName b_product_info
 */
@TableName(value ="b_product_info")
@Data
public class ProductInfoDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 产品利率
     */
    @TableField(value = "rate")
    private BigDecimal rate;

    /**
     * 产品期限
     */
    @TableField(value = "cycle")
    private Integer cycle;

    /**
     * 产品发布时间
     */
    @TableField(value = "release_time")
    private Date releaseTime;

    /**
     * 产品类型 0新手宝，1优选产品，2散标产品
     */
    @TableField(value = "product_type")
    private Integer productType;

    /**
     * 产品编号
     */
    @TableField(value = "product_no")
    private String productNo;

    /**
     * 产品金额
     */
    @TableField(value = "product_money")
    private BigDecimal productMoney;

    /**
     * 产品剩余可投金额
     */
    @TableField(value = "left_product_money")
    private BigDecimal leftProductMoney;

    /**
     * 最低投资金额，即起投金额
     */
    @TableField(value = "bid_min_limit")
    private BigDecimal bidMinLimit;

    /**
     * 最高投资金额，即最多能投多少金额
     */
    @TableField(value = "bid_max_limit")
    private BigDecimal bidMaxLimit;

    /**
     * 产品状态（0未满标，1已满标，2满标已生成收益计划）
     */
    @TableField(value = "product_status")
    private Integer productStatus;

    /**
     * 产品投资满标时间
     */
    @TableField(value = "product_full_time")
    private Date productFullTime;

    /**
     * 产品描述
     */
    @TableField(value = "product_desc")
    private String productDesc;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}