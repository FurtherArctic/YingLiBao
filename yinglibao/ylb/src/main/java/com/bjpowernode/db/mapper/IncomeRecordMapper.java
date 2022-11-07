package com.bjpowernode.db.mapper;

import com.bjpowernode.db.domain.IncomeRecordDO;

/**
* @author wangj
* @description 针对表【b_income_record(收益记录表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:37
* @Entity com.bjpowernode.db.domain.IncomeRecord
*/
public interface IncomeRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IncomeRecordDO record);

    int insertSelective(IncomeRecordDO record);

    IncomeRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IncomeRecordDO record);

    int updateByPrimaryKey(IncomeRecordDO record);

}
