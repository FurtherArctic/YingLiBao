package com.bjpowernode.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.db.domain.RechargeRecordDO;

/**
* @author wangj
* @description 针对表【b_recharge_record(充值记录表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:38
* @Entity com.bjpowernode.db.domain.RechargeRecord
*/
public interface RechargeRecordMapper extends BaseMapper<RechargeRecordDO> {

    int deleteByPrimaryKey(Long id);

    int insert(RechargeRecordDO record);

    int insertSelective(RechargeRecordDO record);

    RechargeRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RechargeRecordDO record);

    int updateByPrimaryKey(RechargeRecordDO record);

}
