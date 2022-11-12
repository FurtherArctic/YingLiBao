package com.bjpowernode.web.struct.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangjunchen
 */
@Data
public class ProductVO {
    private Integer id;
    private String productName;
    private BigDecimal rate;
    private Integer cycle;
    private Integer productType;
    private String productNo;
    private BigDecimal productMoney;
    private BigDecimal leftProductMoney;
    private BigDecimal bidMinLimit;
    private BigDecimal bidMaxLimit;
}
