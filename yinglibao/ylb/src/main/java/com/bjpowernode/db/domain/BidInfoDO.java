package com.bjpowernode.db.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资记录表
 * @TableName b_bid_info
 */
public class BidInfoDO implements Serializable {
    /**
     * 投标记录ID
     */
    private Integer id;

    /**
     * 产品ID
     */
    private Integer prodId;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 投标金额
     */
    private BigDecimal bidMoney;

    /**
     * 投标时间
     */
    private Date bidTime;

    /**
     * 投标状态
     */
    private Integer bidStatus;

    private static final long serialVersionUID = 1L;

    /**
     * 投标记录ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 投标记录ID
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 投标金额
     */
    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    /**
     * 投标金额
     */
    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    /**
     * 投标时间
     */
    public Date getBidTime() {
        return bidTime;
    }

    /**
     * 投标时间
     */
    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    /**
     * 投标状态
     */
    public Integer getBidStatus() {
        return bidStatus;
    }

    /**
     * 投标状态
     */
    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
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
        BidInfoDO other = (BidInfoDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getBidMoney() == null ? other.getBidMoney() == null : this.getBidMoney().equals(other.getBidMoney()))
            && (this.getBidTime() == null ? other.getBidTime() == null : this.getBidTime().equals(other.getBidTime()))
            && (this.getBidStatus() == null ? other.getBidStatus() == null : this.getBidStatus().equals(other.getBidStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getBidMoney() == null) ? 0 : getBidMoney().hashCode());
        result = prime * result + ((getBidTime() == null) ? 0 : getBidTime().hashCode());
        result = prime * result + ((getBidStatus() == null) ? 0 : getBidStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prodId=").append(prodId);
        sb.append(", uid=").append(uid);
        sb.append(", bidMoney=").append(bidMoney);
        sb.append(", bidTime=").append(bidTime);
        sb.append(", bidStatus=").append(bidStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}