package com.hope.blog.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.service.SysDictService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/sys/sysDict")
    public class SysDictController {

    @Autowired
    private SysDictService iSysDictService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysDict>> findListByPage(@ApiParam @RequestBody SysDictSearchRequestDto sysDictSearchRequestDto){
        Page<SysDict> list=iSysDictService.findListByPage(sysDictSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<SysDict> getById(@PathVariable String id){
    SysDict entity=iSysDictService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody SysDict entity){
        boolean success=iSysDictService.saveOrUpdate(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除单条记录
     */
    @ApiOperation(value = "删除单条记录")
    @GetMapping(value = "/delete/{id}")
    public CommonResult<ResultCode> delete(@PathVariable String id){
        boolean success=iSysDictService.removeById(id);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
    }

     /**
     * 修改单条记录
     */
     @ApiOperation(value = "修改单条记录")
     @PostMapping(value = "/update")
     public CommonResult<ResultCode> update(@ApiParam @RequestBody SysDict entity){
        boolean success=iSysDictService.updateById(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
     }
}

