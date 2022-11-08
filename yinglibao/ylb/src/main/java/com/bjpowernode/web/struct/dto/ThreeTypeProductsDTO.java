package com.bjpowernode.web.struct.dto;

import com.bjpowernode.db.domain.ProductInfoDO;
import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangjunchen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreeTypeProductsDTO {
    private List<ProductInfoDO> newList;
    private List<ProductInfoDO> goodList;
    private List<ProductInfoDO> bulkList;
}
