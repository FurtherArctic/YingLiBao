package com.bjpowernode.web.service;

import com.bjpowernode.web.struct.dto.BidRankDTO;

import java.util.List;

/**
 * @author wangjunchen
 */

public interface BidService {
    /**
     *
     * @return
     */
    List<BidRankDTO> getBidMoneyRanks();
}
