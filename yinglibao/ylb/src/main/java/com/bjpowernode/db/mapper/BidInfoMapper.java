package com.bjpowernode.db.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.db.domain.BidInfoDO;
import org.springframework.stereotype.Component;

/**
* @author wangj
* @description 针对表【b_bid_info(投资记录表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:37
* @Entity com.bjpowernode.db.domain.BidInfo
*/
@Component
public interface BidInfoMapper extends BaseMapper<BidInfoDO> {

    int deleteByPrimaryKey(Long id);

    int insert(BidInfoDO record);

    int insertSelective(BidInfoDO record);

    BidInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BidInfoDO record);

    int updateByPrimaryKey(BidInfoDO record);

}
