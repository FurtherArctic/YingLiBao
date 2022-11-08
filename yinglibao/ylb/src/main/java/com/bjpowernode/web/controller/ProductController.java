package com.bjpowernode.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.db.domain.ProductInfoDO;
import com.bjpowernode.web.service.ProductsService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;
import com.bjpowernode.web.struct.vo.ProductVO;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "查询产品信息",notes = "一个新手宝，三个优选，三个散标")
    @RequestMapping("/products/three")
    @ApiResponses({
            @ApiResponse(code = 200,message = "请求成功",response = CommonResult.class),
            @ApiResponse(code = 401,message = "不能访问系统"),
            @ApiResponse(code =403,message="权限不足"),
            @ApiResponse(code =404,message="找不到服务器"),
            @ApiResponse(code = 500,message = "服务器错误，请联系张三")
    })
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
