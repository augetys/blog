package com.hope.blog.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.sys.dto.request.SysDictDetailSearchRequestDto;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.service.SysDictService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Resource
    private SysDictService iSysDictService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysDict>> findListByPage(@ApiParam @RequestBody SysDictSearchRequestDto sysDictSearchRequestDto) {
        IPage<SysDict> list = iSysDictService.findListByPage(sysDictSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增字典")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid SysDict entity) {
        boolean success = iSysDictService.saveOrUpdate(entity);
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
    @OperationLog(value = "删除字典（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iSysDictService.removeById(id);
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
    @OperationLog(value = "修改字典")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid SysDict entity) {
        boolean success = iSysDictService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据字典id查询字典详情")
    @PostMapping(value = "/getDetailById")
    public CommonResult<List<SysDictDetail>> getDetailById(@ApiParam @RequestBody SysDictDetailSearchRequestDto sysDictDetailSearchRequestDto) {
        List<SysDictDetail> entity = iSysDictService.getDetailById(sysDictDetailSearchRequestDto);
        return CommonResult.success(entity);
    }

    /**
     * 根据字典名称查询数据
     */
    @ApiOperation(value = "根据字典名称查询数据")
    @PostMapping(value = "/getDetailByNames")
    public CommonResult<Map<String, List<SysDictDetail>>> getDetailByNames(@ApiParam @RequestBody List<String> nameLists) {
        Map<String, List<SysDictDetail>> map = iSysDictService.getDetailByNames(nameLists);
        return CommonResult.success(map);
    }
}

