package com.bjpowernode.web.struct.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangjunchen
 */
@Data
@Builder
public class BaseInfoDTO {
    /**
     * 注册用户数
     */
    private Long registerUserCount;
    /**
     * 总投资金额
     */
    private BigDecimal sumBidMoney;
    /**
     * 平均利率
     */
    private BigDecimal avgRate;
}
