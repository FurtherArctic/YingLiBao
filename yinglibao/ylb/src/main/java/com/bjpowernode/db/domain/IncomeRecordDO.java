package com.bjpowernode.db.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收益记录表
 * @TableName b_income_record
 */
public class IncomeRecordDO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 产品ID
     */
    private Integer prodId;

    /**
     * 投标记录ID
     */
    private Integer bidId;

    /**
     * 投资金额
     */
    private BigDecimal bidMoney;

    /**
     * 期到时间
     */
    private Date incomeDate;

    /**
     * 收益金额
     */
    private BigDecimal incomeMoney;

    /**
     * 收益状态（0未返，1已返）
     */
    private Integer incomeStatus;

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
     * 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 产品ID
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * 产品ID
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    /**
     * 投标记录ID
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * 投标记录ID
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /**
     * 投资金额
     */
    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    /**
     * 投资金额
     */
    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    /**
     * 期到时间
     */
    public Date getIncomeDate() {
        return incomeDate;
    }

    /**
     * 期到时间
     */
    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    /**
     * 收益金额
     */
    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    /**
     * 收益金额
     */
    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    /**
     * 收益状态（0未返，1已返）
     */
    public Integer getIncomeStatus() {
        return incomeStatus;
    }

    /**
     * 收益状态（0未返，1已返）
     */
    public void setIncomeStatus(Integer incomeStatus) {
        this.incomeStatus = incomeStatus;
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
        IncomeRecordDO other = (IncomeRecordDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getBidId() == null ? other.getBidId() == null : this.getBidId().equals(other.getBidId()))
            && (this.getBidMoney() == null ? other.getBidMoney() == null : this.getBidMoney().equals(other.getBidMoney()))
            && (this.getIncomeDate() == null ? other.getIncomeDate() == null : this.getIncomeDate().equals(other.getIncomeDate()))
            && (this.getIncomeMoney() == null ? other.getIncomeMoney() == null : this.getIncomeMoney().equals(other.getIncomeMoney()))
            && (this.getIncomeStatus() == null ? other.getIncomeStatus() == null : this.getIncomeStatus().equals(other.getIncomeStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getBidId() == null) ? 0 : getBidId().hashCode());
        result = prime * result + ((getBidMoney() == null) ? 0 : getBidMoney().hashCode());
        result = prime * result + ((getIncomeDate() == null) ? 0 : getIncomeDate().hashCode());
        result = prime * result + ((getIncomeMoney() == null) ? 0 : getIncomeMoney().hashCode());
        result = prime * result + ((getIncomeStatus() == null) ? 0 : getIncomeStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", prodId=").append(prodId);
        sb.append(", bidId=").append(bidId);
        sb.append(", bidMoney=").append(bidMoney);
        sb.append(", incomeDate=").append(incomeDate);
        sb.append(", incomeMoney=").append(incomeMoney);
        sb.append(", incomeStatus=").append(incomeStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}