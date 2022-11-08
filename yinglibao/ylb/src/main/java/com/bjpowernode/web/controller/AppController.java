package com.bjpowernode.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bjpowernode.web.service.AppService;
import com.bjpowernode.web.struct.CommonResult;
import com.bjpowernode.web.struct.dto.BaseInfoDTO;
import com.bjpowernode.web.struct.vo.BaseInfoVO;
import io.swagger.annotations.*;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.NotificationEmitter;

/**
 * tags 给api接口，分组名称，默认当前controller中所有接口都属于tags分组
 * 多个类的Api.tags相同时，也放到同一tags下
 *
 * @author wangjunchen
 */
@Api(tags = "应用程序基本信息接口")
@RestController
public class AppController {
    private AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    /**
     * 注解@ApiOperation表示接口的功能
     * value：功能简短描述
     * notes: 详细描述（可选）
     *
     * @return dto
     */
    @ApiOperation(value = "三项数据", notes = "三项基本数据：用户数量，累计投资金额，平均利率")
    @ApiResponses({
            @ApiResponse(code = 200,message = "请求成功",response = CommonResult.class),
            @ApiResponse(code = 401,message = "不能访问系统"),
            @ApiResponse(code =403,message="权限不足"),
            @ApiResponse(code =404,message="找不到服务器"),
            @ApiResponse(code = 500,message = "服务器错误，请联系张三")
    })
    @RequestMapping("/app/base/info")
    public CommonResult getBaseInfo() {
        BaseInfoDTO baseInfoDTO = appService.queryBaseInfo();
        //将service提供的DTO数据转换为提供给vue的VO数据，copyProperties只适用于同名属性拷贝
        BaseInfoVO baseInfoVO = BeanUtil.copyProperties(baseInfoDTO, BaseInfoVO.class);

        return CommonResult.success(baseInfoVO);
    }
}
