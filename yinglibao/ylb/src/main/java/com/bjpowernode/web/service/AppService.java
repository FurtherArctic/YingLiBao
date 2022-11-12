package com.bjpowernode.web.service;

import com.bjpowernode.web.struct.dto.BaseInfoDTO;

/**
 * @author wangjunchen
 */
public interface AppService {
    /**
     * 获取三项数据：年利率，投资额，用户数量
     *
     * @return BaseInfoDTO对象
     */
    BaseInfoDTO queryBaseInfo();
}
