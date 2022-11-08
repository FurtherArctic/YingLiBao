package com.bjpowernode.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.db.domain.BidInfoDO;
import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.db.mapper.BidInfoMapper;
import com.bjpowernode.db.mapper.ProductInfoMapper;
import com.bjpowernode.db.mapper.UserMapper;
import com.bjpowernode.web.service.AppService;
import com.bjpowernode.web.struct.dto.BaseInfoDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wangjunchen
 */
@Service
public class AppServiceImpl implements AppService {
    private UserMapper userMapper;
    private ProductInfoMapper productInfoMapper;
    private BidInfoMapper bidInfoMapper;


    public AppServiceImpl(UserMapper userMapper, ProductInfoMapper productInfoMapper, BidInfoMapper bidInfoMapper) {
        this.userMapper = userMapper;
        this.productInfoMapper = productInfoMapper;
        this.bidInfoMapper = bidInfoMapper;
    }

    @Override
    public BaseInfoDTO queryBaseInfo() {
        Long counts = userMapper.selectUserCounts();

        //利率
        QueryWrapper<ProductInfoDO> con = new QueryWrapper<>();
        con.select(" round(avg(rate),2) as rate");
        ProductInfoDO product = productInfoMapper.selectOne(con);

        //总投资额
        QueryWrapper<BidInfoDO> wrapper = new QueryWrapper<>();
        wrapper.select("sum(bid_money) as bid_money");
        BidInfoDO bidInfo = bidInfoMapper.selectOne(wrapper);

        return BaseInfoDTO.builder().registerUserCount(counts).sumBidMoney(bidInfo.getBidMoney()).avgRate(product.getRate()).build();
    }
}
