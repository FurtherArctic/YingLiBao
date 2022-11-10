package com.bjpowernode.web.service;

import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;

import java.util.List;

/**
 * @author wangjunchen
 */
public interface ProductsService {
    ThreeTypeProductsDTO queryPageByProductType();
    List<ProductInfoDO> findPageByProductType(Integer productType, Integer pageNo);
    Long countByProductType(Integer productType);
}
