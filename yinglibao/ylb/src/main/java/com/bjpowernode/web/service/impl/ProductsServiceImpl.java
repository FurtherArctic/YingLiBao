package com.bjpowernode.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.common.consts.AppConsts;
import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.db.mapper.ProductInfoMapper;
import com.bjpowernode.web.service.ProductsService;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjunchen
 */
@Service
public class ProductsServiceImpl implements ProductsService {
    private ProductInfoMapper productInfoMapper;

    public ProductsServiceImpl(ProductInfoMapper productInfoMapper) {
        this.productInfoMapper = productInfoMapper;
    }

    @Override
    public ThreeTypeProductsDTO queryPageByProductType() {
        //新手宝
        List<ProductInfoDO> newList = productInfoMapper.selectPageByProductType(AppConsts.PRODUCT_TYPE_NEW, 0, 1);
        //优选
        List<ProductInfoDO> goodList = productInfoMapper.selectPageByProductType(AppConsts.PRODUCT_TYPE_GOOD, 0, 3);
        //散标
        List<ProductInfoDO> bulkList = productInfoMapper.selectPageByProductType(AppConsts.PRODUCT_TYPE_BULK, 0, 3);
        return new ThreeTypeProductsDTO(newList, goodList, bulkList);
    }

    @Override
    public List<ProductInfoDO> findPageByProductType(Integer productType, Integer pageNo) {

        int offset = (pageNo - 1) * AppConsts.PAGE_SIZE;
        return productInfoMapper.selectPageByProductType(productType, offset, AppConsts.PAGE_SIZE);
    }

    @Override
    public Long countByProductType(Integer productType) {
        QueryWrapper<ProductInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_type", productType);
        return productInfoMapper.selectCount(queryWrapper);
    }
}
