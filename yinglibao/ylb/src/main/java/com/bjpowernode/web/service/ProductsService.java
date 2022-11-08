package com.bjpowernode.web.service;

import com.bjpowernode.web.struct.dto.ThreeTypeProductsDTO;

/**
 * @author wangjunchen
 */
public interface ProductsService {
    ThreeTypeProductsDTO queryPageByProductType();
}
