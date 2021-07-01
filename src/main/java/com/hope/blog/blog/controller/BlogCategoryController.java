package com.hope.blog.blog.controller;

import com.hope.blog.blog.model.BlogCategory;
import com.hope.blog.blog.service.BlogCategoryService;
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

import java.util.List;

/**
 * <p>
 * 博客分类表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
@Api(tags = "博客分类表")
@RestController
@RequestMapping("/blog/blogCategory")
    public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogCategory>> findListByPage(@ApiParam @RequestBody BlogCategory entity){
        List<BlogCategory> list=blogCategoryService.findListByPage(entity);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogCategory> getById(@PathVariable String id){
    BlogCategory entity=blogCategoryService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogCategory entity){
        boolean success=blogCategoryService.saveOrUpdate(entity);
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
        boolean success=blogCategoryService.removeById(id);
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
     public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogCategory entity){
        boolean success=blogCategoryService.updateById(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
     }
}
