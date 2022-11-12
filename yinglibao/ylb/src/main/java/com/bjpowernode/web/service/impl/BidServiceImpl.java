package com.bjpowernode.web.service.impl;

import com.bjpowernode.common.redis.RedisAssist;
import com.bjpowernode.common.redis.RedisKey;
import com.bjpowernode.web.service.BidService;
import com.bjpowernode.web.struct.dto.BidRankDTO;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangjunchen
 */
@Service
public class BidServiceImpl implements BidService {
    @Resource
    private RedisAssist redisAssist;

    /**
     * 获取投资排行前三位，手机号和投资额，手机号注意脱敏处理，保护用户隐私
     *
     * @return BidRankDTO对象
     */
    @Override
    public List<BidRankDTO> getBidMoneyRanks() {
        Set<ZSetOperations.TypedTuple<String>> sets =
                redisAssist.getReverseFromZSet(RedisKey.BID_MONEY_RANK, 0, 2);
        List<BidRankDTO> rankList = new ArrayList<>();
        sets.forEach(rank ->
                rankList.add(new BidRankDTO(rank.getValue(), rank.getScore())));
        return rankList;
    }
}
