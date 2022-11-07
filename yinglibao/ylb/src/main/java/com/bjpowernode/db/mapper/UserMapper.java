package com.bjpowernode.db.mapper;

import com.bjpowernode.db.domain.UserDO;

/**
* @author wangj
* @description 针对表【u_user(用户表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:53
* @Entity com.bjpowernode.db.domain.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

}
