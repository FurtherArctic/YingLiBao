package com.bjpowernode.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.web.service.BidService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.dto.BidRankDTO;
import com.bjpowernode.web.struct.vo.BidRankVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangjunchen
 */
@Api(tags = "投资业务")
@RestController
public class BidController {
    @Resource
    private BidService bidService;

    @ApiOperation(value = "")
    @RequestMapping("/bid/rank")
    public CommonResult showBidMoneyRank() {
        List<BidRankDTO> bidMoneyRanks = bidService.getBidMoneyRanks();

        List<BidRankVO> bidRankVOS = BeanUtil.copyToList(bidMoneyRanks, BidRankVO.class);
        return CommonResult.success(bidRankVOS);
    }
}
