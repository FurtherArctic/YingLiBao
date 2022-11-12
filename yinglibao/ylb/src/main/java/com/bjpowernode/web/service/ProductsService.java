package com.bjpowernode.web.service;

import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;

import java.util.List;

/**
 * @author wangjunchen
 */
public interface ProductsService {
    /**
     * 根据产品类型获取数据，新手宝1个，优选3个，散标3个
     *
     * @return ThreeTypeProductsDTO数据，属性分别时三种产品的集合
     */
    ThreeTypeProductsDTO queryPageByProductType();

    /**
     * 根据前端接口提供的类型和分页参数，从数据库中查询相关的数据，存入集合中
     *
     * @param productType 产品类型
     * @param pageNo      产品分页，即第几页数据
     * @return 产品信息ProductInfoDO集合
     */
    List<ProductInfoDO> findPageByProductType(Integer productType, Integer pageNo);

    /**
     * 统计不同类型产品的总数
     *
     * @param productType 产品类型
     * @return Long型数据
     */
    Long countByProductType(Integer productType);
}
