package com.bjpowernode.web.struct.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wangjunchen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseInfoVO {
    private Long registerUserCount;
    private BigDecimal sumBidMoney;
    private BigDecimal avgRate;
}
