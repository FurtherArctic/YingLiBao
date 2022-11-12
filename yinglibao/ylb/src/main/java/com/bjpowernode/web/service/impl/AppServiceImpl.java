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

import javax.annotation.Resource;


/**
 * @author wangjunchen
 */
@Service
public class AppServiceImpl implements AppService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;

    /**
     * 从数据库中获取三项数据
     *
     * @return BaseInfoDTO
     */

    @Override
    public BaseInfoDTO queryBaseInfo() {
        //用户数量
        Long counts = userMapper.selectCount(null);
        //利率
        QueryWrapper<ProductInfoDO> con = new QueryWrapper<>();
        con.select(" round(avg(rate),2) as rate");
        ProductInfoDO product = productInfoMapper.selectOne(con);
        //总投资额
        QueryWrapper<BidInfoDO> wrapper = new QueryWrapper<>();
        wrapper.select("sum(bid_money) as bid_money");
        BidInfoDO bidInfo = bidInfoMapper.selectOne(wrapper);

        //链式编程，数据封装到BaseInfoDTO对象中
        return BaseInfoDTO.builder().registerUserCount(counts).sumBidMoney(bidInfo.getBidMoney()).avgRate(product.getRate()).build();
    }
}
