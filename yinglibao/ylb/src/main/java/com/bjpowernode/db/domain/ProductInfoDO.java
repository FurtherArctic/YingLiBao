package com.bjpowernode.db.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息表
 * @TableName b_product_info
 */
public class ProductInfoDO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品利率
     */
    private BigDecimal rate;

    /**
     * 产品期限
     */
    private Integer cycle;

    /**
     * 产品发布时间
     */
    private Date releaseTime;

    /**
     * 产品类型 0新手宝，1优选产品，2散标产品
     */
    private Integer productType;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 产品金额
     */
    private BigDecimal productMoney;

    /**
     * 产品剩余可投金额
     */
    private BigDecimal leftProductMoney;

    /**
     * 最低投资金额，即起投金额
     */
    private BigDecimal bidMinLimit;

    /**
     * 最高投资金额，即最多能投多少金额
     */
    private BigDecimal bidMaxLimit;

    /**
     * 产品状态（0未满标，1已满标，2满标已生成收益计划）
     */
    private Integer productStatus;

    /**
     * 产品投资满标时间
     */
    private Date productFullTime;

    /**
     * 产品描述
     */
    private String productDesc;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 产品利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 产品利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 产品期限
     */
    public Integer getCycle() {
        return cycle;
    }

    /**
     * 产品期限
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * 产品发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 产品发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 产品类型 0新手宝，1优选产品，2散标产品
     */
    public Integer getProductType() {
        return productType;
    }

    /**
     * 产品类型 0新手宝，1优选产品，2散标产品
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    /**
     * 产品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 产品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 产品金额
     */
    public BigDecimal getProductMoney() {
        return productMoney;
    }

    /**
     * 产品金额
     */
    public void setProductMoney(BigDecimal productMoney) {
        this.productMoney = productMoney;
    }

    /**
     * 产品剩余可投金额
     */
    public BigDecimal getLeftProductMoney() {
        return leftProductMoney;
    }

    /**
     * 产品剩余可投金额
     */
    public void setLeftProductMoney(BigDecimal leftProductMoney) {
        this.leftProductMoney = leftProductMoney;
    }

    /**
     * 最低投资金额，即起投金额
     */
    public BigDecimal getBidMinLimit() {
        return bidMinLimit;
    }

    /**
     * 最低投资金额，即起投金额
     */
    public void setBidMinLimit(BigDecimal bidMinLimit) {
        this.bidMinLimit = bidMinLimit;
    }

    /**
     * 最高投资金额，即最多能投多少金额
     */
    public BigDecimal getBidMaxLimit() {
        return bidMaxLimit;
    }

    /**
     * 最高投资金额，即最多能投多少金额
     */
    public void setBidMaxLimit(BigDecimal bidMaxLimit) {
        this.bidMaxLimit = bidMaxLimit;
    }

    /**
     * 产品状态（0未满标，1已满标，2满标已生成收益计划）
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * 产品状态（0未满标，1已满标，2满标已生成收益计划）
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 产品投资满标时间
     */
    public Date getProductFullTime() {
        return productFullTime;
    }

    /**
     * 产品投资满标时间
     */
    public void setProductFullTime(Date productFullTime) {
        this.productFullTime = productFullTime;
    }

    /**
     * 产品描述
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * 产品描述
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductInfoDO other = (ProductInfoDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getCycle() == null ? other.getCycle() == null : this.getCycle().equals(other.getCycle()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType()))
            && (this.getProductNo() == null ? other.getProductNo() == null : this.getProductNo().equals(other.getProductNo()))
            && (this.getProductMoney() == null ? other.getProductMoney() == null : this.getProductMoney().equals(other.getProductMoney()))
            && (this.getLeftProductMoney() == null ? other.getLeftProductMoney() == null : this.getLeftProductMoney().equals(other.getLeftProductMoney()))
            && (this.getBidMinLimit() == null ? other.getBidMinLimit() == null : this.getBidMinLimit().equals(other.getBidMinLimit()))
            && (this.getBidMaxLimit() == null ? other.getBidMaxLimit() == null : this.getBidMaxLimit().equals(other.getBidMaxLimit()))
            && (this.getProductStatus() == null ? other.getProductStatus() == null : this.getProductStatus().equals(other.getProductStatus()))
            && (this.getProductFullTime() == null ? other.getProductFullTime() == null : this.getProductFullTime().equals(other.getProductFullTime()))
            && (this.getProductDesc() == null ? other.getProductDesc() == null : this.getProductDesc().equals(other.getProductDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getCycle() == null) ? 0 : getCycle().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getProductNo() == null) ? 0 : getProductNo().hashCode());
        result = prime * result + ((getProductMoney() == null) ? 0 : getProductMoney().hashCode());
        result = prime * result + ((getLeftProductMoney() == null) ? 0 : getLeftProductMoney().hashCode());
        result = prime * result + ((getBidMinLimit() == null) ? 0 : getBidMinLimit().hashCode());
        result = prime * result + ((getBidMaxLimit() == null) ? 0 : getBidMaxLimit().hashCode());
        result = prime * result + ((getProductStatus() == null) ? 0 : getProductStatus().hashCode());
        result = prime * result + ((getProductFullTime() == null) ? 0 : getProductFullTime().hashCode());
        result = prime * result + ((getProductDesc() == null) ? 0 : getProductDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", rate=").append(rate);
        sb.append(", cycle=").append(cycle);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", productType=").append(productType);
        sb.append(", productNo=").append(productNo);
        sb.append(", productMoney=").append(productMoney);
        sb.append(", leftProductMoney=").append(leftProductMoney);
        sb.append(", bidMinLimit=").append(bidMinLimit);
        sb.append(", bidMaxLimit=").append(bidMaxLimit);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", productFullTime=").append(productFullTime);
        sb.append(", productDesc=").append(productDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}