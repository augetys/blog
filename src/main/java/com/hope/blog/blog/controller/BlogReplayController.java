package com.hope.blog.blog.controller;

import com.hope.blog.blog.model.BlogReplay;
import com.hope.blog.blog.service.BlogReplayService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客文章回复表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Api(tags = "博客文章回复表")
@RestController
@RequestMapping("/blog/replay")
    public class BlogReplayController {

    @Autowired
    private BlogReplayService iBlogReplayService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogReplay>> findListByPage(@ApiParam @RequestBody BlogReplay entity){
        List<BlogReplay> list=iBlogReplayService.findListByPage(entity);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogReplay> getById(@PathVariable String id){
    BlogReplay entity=iBlogReplayService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogReplay entity){
        boolean success=iBlogReplayService.saveOrUpdate(entity);
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
        boolean success=iBlogReplayService.removeById(id);
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
     public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogReplay entity){
        boolean success=iBlogReplayService.updateById(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
     }
}

