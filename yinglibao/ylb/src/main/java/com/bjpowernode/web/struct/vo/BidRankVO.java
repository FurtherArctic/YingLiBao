package com.bjpowernode.web.struct.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunchen
 */

public class BidRankVO {
    private String phone;
    private Double score;

    public String getPhone() {
        //数据隐藏脱敏
        if (phone != null && phone.length() >= 11) {
            phone = phone.substring(0, 3) + "******" + phone.substring(9);
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
