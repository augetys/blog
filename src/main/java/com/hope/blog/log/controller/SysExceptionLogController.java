package com.hope.blog.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.log.dto.request.SysExceptionLogSearchRequestDto;
import com.hope.blog.log.model.SysExceptionLog;
import com.hope.blog.log.service.SysExceptionLogService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 异常日志表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-06
 */
@Api(tags = "异常日志表")
@RestController
@RequestMapping("/log/exception")
public class SysExceptionLogController {

    @Autowired
    private SysExceptionLogService iSysExceptionLogService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysExceptionLog>> findListByPage(@ApiParam @RequestBody SysExceptionLogSearchRequestDto sysExceptionLogSearchRequestDto) {
        IPage<SysExceptionLog> list = iSysExceptionLogService.findListByPage(sysExceptionLogSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }
}

