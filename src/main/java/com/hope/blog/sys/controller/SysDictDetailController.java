package com.hope.blog.sys.controller;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.sys.dto.request.UpdateDictDetailStatusRequetDto;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.service.SysDictDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lijin on  2021/7/16
 */
@Api(tags = "数据字典详情")
@RestController
@RequestMapping("/sys/dictDetail")
public class SysDictDetailController {

    @Resource
    private SysDictDetailService iSysDictDetailService;

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增字典详情")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid SysDictDetail entity) {
        boolean success = iSysDictDetailService.saveOrUpdate(entity);
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
    @OperationLog(value = "删除字典详情（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iSysDictDetailService.removeById(id);
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
    @OperationLog(value = "修改字典详情")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid SysDictDetail entity) {
        boolean success = iSysDictDetailService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改单条记录
     */
    @ApiOperation(value = "修改状态")
    @PostMapping(value = "/updateStatus")
    @OperationLog(value = "修改字典详情状态")
    public CommonResult<ResultCode> updateStatus(@ApiParam @RequestBody @Valid UpdateDictDetailStatusRequetDto updateDictDetailStatusRequetDto) {
        boolean success = iSysDictDetailService.updateStatusById(updateDictDetailStatusRequetDto);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}
