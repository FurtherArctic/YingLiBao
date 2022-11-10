package com.bjpowernode.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.db.domain.ProductInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author wangj
 * @description 针对表【b_product_info(产品信息表)】的数据库操作Mapper
 * @createDate 2022-11-07 19:12:38
 * @Entity com.bjpowernode.db.domain.ProductInfo
 */
@Component
public interface ProductInfoMapper extends BaseMapper<ProductInfoDO> {

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfoDO record);

    int insertSelective(ProductInfoDO record);

    ProductInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfoDO record);

    int updateByPrimaryKey(ProductInfoDO record);

    /**
     * 查询数据，根据类型，先按时间倒序，然后利率倒序
     *
     * @param type   产品类型
     * @param offset 查询起始下标
     * @param rows   查询的数据数量
     * @return List<ProductInfoDO> 数据集合
     */
    List<ProductInfoDO> selectPageByProductType(@Param("type") Integer type,
                                                @Param("offset") Integer offset,
                                                @Param("rows") Integer rows);

}
