package com.bjpowernode.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.web.service.ProductsService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;
import com.bjpowernode.web.struct.vo.ProductVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjunchen
 */
@Api(tags = "理财产品模块")
@RestController
public class ProductController {
    private ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * @return
     */
    @RequestMapping("/products/three")
    public CommonResult getThreeProducts() {

        ThreeTypeProductsDTO threeTypeProductsDTO = productsService.queryPageByProductType();

        //DTO转换为VO
        List<ProductVO> newList = BeanUtil.copyToList(threeTypeProductsDTO.getNewList(), ProductVO.class);
        List<ProductVO> goodList = BeanUtil.copyToList(threeTypeProductsDTO.getGoodList(), ProductVO.class);
        List<ProductVO> bulkList = BeanUtil.copyToList(threeTypeProductsDTO.getBulkList(), ProductVO.class);

        //封装数据
        Map<String, Object> data = new HashMap<>();
        data.put("newList", newList);
        data.put("goodList", goodList);
        data.put("bulkList", bulkList);
        return CommonResult.success(data);
    }
}
