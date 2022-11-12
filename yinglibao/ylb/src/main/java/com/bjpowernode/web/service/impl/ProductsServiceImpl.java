package com.bjpowernode.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.common.consts.AppConstants;
import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.db.mapper.ProductInfoMapper;
import com.bjpowernode.web.service.ProductsService;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangjunchen
 */
@Service
public class ProductsServiceImpl implements ProductsService {
    @Resource
    private ProductInfoMapper productInfoMapper;

    /**
     * 首页展示的数据，1个新手宝，3个优选，3个散标，利润高的产品放到首页上，吸引客户
     *
     * @return ThreeTypeProductsDTO对象，对象变量为三种产品对应的集合
     */
    @Override
    public ThreeTypeProductsDTO queryPageByProductType() {
        //新手宝
        List<ProductInfoDO> newList = productInfoMapper.selectPageByProductType(AppConstants.PRODUCT_TYPE_NEW, 0, 1);
        //优选
        List<ProductInfoDO> goodList = productInfoMapper.selectPageByProductType(AppConstants.PRODUCT_TYPE_GOOD, 0, 3);
        //散标
        List<ProductInfoDO> bulkList = productInfoMapper.selectPageByProductType(AppConstants.PRODUCT_TYPE_BULK, 0, 3);
        return new ThreeTypeProductsDTO(newList, goodList, bulkList);
    }

    /**
     * 根据产品类型和产品分页从数据库中查询数据
     *
     * @param productType 产品类型
     * @param pageNo      产品分页
     * @return 产品信息ProductInfoDO集合
     */
    @Override
    public List<ProductInfoDO> findPageByProductType(Integer productType, Integer pageNo) {
        //通过页数获取查询数据中的limit第一个参数，即起始下标，第二个参数即每一页的数据量
        int offset = (pageNo - 1) * AppConstants.PAGE_SIZE;
        return productInfoMapper.selectPageByProductType(productType, offset, AppConstants.PAGE_SIZE);
    }

    /**
     * 某一类型产品数量
     *
     * @param productType 产品类型
     * @return Long，产品数量
     */
    @Override
    public Long countByProductType(Integer productType) {
        QueryWrapper<ProductInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_type", productType);
        return productInfoMapper.selectCount(queryWrapper);
    }
}
