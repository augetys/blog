package com.hope.blog.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.log.dto.request.SysLogSearchRequestDto;
import com.hope.blog.log.model.SysLog;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.service.SysLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-06-22
 */
@Api(tags = "操作日志表")
@RestController
@RequestMapping("/log/operation")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysLog>> findListByPage(@ApiParam @RequestBody SysLogSearchRequestDto sysLog) {
        IPage<SysLog> list = sysLogService.findListByPage(sysLog);
        return CommonResult.success(CommonPage.restPage(list));
    }
}

