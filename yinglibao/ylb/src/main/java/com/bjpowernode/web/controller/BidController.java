package com.bjpowernode.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.web.service.BidService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.dto.BidRankDTO;
import com.bjpowernode.web.struct.vo.BidRankVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "投资排行榜",notes = "投资金额最高的用户排行，手机号脱敏处理")
    @ApiResponses({
            @ApiResponse(code = 200,message = "请求成功",response = CommonResult.class),
            @ApiResponse(code = 401,message = "不能访问系统"),
            @ApiResponse(code =403,message="权限不足"),
            @ApiResponse(code =404,message="找不到服务器"),
            @ApiResponse(code = 500,message = "服务器错误，请联系张三")
    })
    @RequestMapping("/bid/rank")
    public CommonResult showBidMoneyRank() {
        List<BidRankDTO> bidMoneyRanks = bidService.getBidMoneyRanks();

        List<BidRankVO> bidRankVOS = BeanUtil.copyToList(bidMoneyRanks, BidRankVO.class);
        return CommonResult.success(bidRankVOS);
    }
}
