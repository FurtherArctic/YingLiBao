package com.bjpowernode.db.mapper;

import com.bjpowernode.db.domain.ProductInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangj
 * @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
 * @createDate 2022-11-12 10:44:45
 * @Entity com.bjpowernode.db.domain.ProductInfo
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfoDO> {

    /**
     * 分页查询产品类型
     *
     * @param type   产品类型
     * @param offset 起始下标
     * @param rows   数据记录数量
     * @return ProductInfoDO集合
     */
    List<ProductInfoDO> selectPageByProductType(@Param("type") Integer type,
                                                @Param("offset") Integer offset,
                                                @Param("rows") Integer rows);
}




