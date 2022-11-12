package com.bjpowernode.web.service;

import com.bjpowernode.web.struct.dto.BidRankDTO;

import java.util.List;

/**
 * @author wangjunchen
 */

public interface BidService {
    /**
     * 获取投资排行榜
     *
     * @return BidRankDTO对象
     */
    List<BidRankDTO> getBidMoneyRanks();
}
