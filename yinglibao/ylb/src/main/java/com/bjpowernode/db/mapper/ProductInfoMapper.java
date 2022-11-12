package com.bjpowernode.db.mapper;

import com.bjpowernode.db.domain.ProductInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author wangj
* @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
* @createDate 2022-11-12 10:44:45
* @Entity com.bjpowernode.db.domain.ProductInfo
*/
public interface ProductInfoMapper extends BaseMapper<ProductInfoDO> {

    List<ProductInfoDO> selectPageByProductType(int productTypeNew, int i, int i1);
}




