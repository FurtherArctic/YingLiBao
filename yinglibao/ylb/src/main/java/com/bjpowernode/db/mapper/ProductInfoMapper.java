package com.bjpowernode.db.mapper;

import com.bjpowernode.db.domain.ProductInfoDO;

/**
* @author wangj
* @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
* @createDate 2022-11-07 19:12:38
* @Entity com.bjpowernode.db.domain.ProductInfo
*/
public interface ProductInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfoDO record);

    int insertSelective(ProductInfoDO record);

    ProductInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfoDO record);

    int updateByPrimaryKey(ProductInfoDO record);

}
