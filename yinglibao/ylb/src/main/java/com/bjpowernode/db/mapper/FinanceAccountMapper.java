package com.bjpowernode.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.db.domain.FinanceAccountDO;

/**
* @author wangj
* @description 针对表【u_finance_account(用户财务资金账户表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:53
* @Entity com.bjpowernode.db.domain.FinanceAccount
*/
public interface FinanceAccountMapper extends BaseMapper<FinanceAccountDO> {

    int deleteByPrimaryKey(Long id);

    int insert(FinanceAccountDO record);

    int insertSelective(FinanceAccountDO record);

    FinanceAccountDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceAccountDO record);

    int updateByPrimaryKey(FinanceAccountDO record);

}
