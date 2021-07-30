package com.hope.blog.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysLog>> findListByPage(@ApiParam @RequestBody SysLogSearchRequestDto sysLog) {
        Page<SysLog> list = sysLogService.findListByPage(sysLog);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<SysLog> getById(@PathVariable String id) {
        SysLog entity = sysLogService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody SysLog entity) {
        boolean success = sysLogService.saveOrUpdate(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除单条记录
     */
    @ApiOperation(value = "删除单条记录")
    @GetMapping(value = "/delete/{id}")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = sysLogService.removeById(id);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改单条记录
     */
    @ApiOperation(value = "修改单条记录")
    @PostMapping(value = "/update")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody SysLog entity) {
        boolean success = sysLogService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

