package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleNavSearchRequestDto;
import com.hope.blog.blog.model.BlogNav;
import com.hope.blog.blog.service.BlogNavService;
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
 * 导航 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
@Api(tags = "导航")
@RestController
@RequestMapping("/blog/nav")
public class BlogNavController {

    @Autowired
    private BlogNavService iBlogNavService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogNav>> findListByPage(@ApiParam @RequestBody BlogArticleNavSearchRequestDto blogArticleNavSearchRequestDto) {
        Page<BlogNav> list = iBlogNavService.findListByPage(blogArticleNavSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 查询所有导航
     */
    @ApiOperation(value = "查询所有导航")
    @GetMapping(value = "/findAll")
    public CommonResult<List<BlogNav>> findAll() {
        List<BlogNav> list = iBlogNavService.findAll();
        return CommonResult.success(list);
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogNav> getById(@PathVariable String id) {
        BlogNav entity = iBlogNavService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogNav entity) {
        boolean success = iBlogNavService.saveOrUpdate(entity);
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
        boolean success = iBlogNavService.removeById(id);
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
    public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogNav entity) {
        boolean success = iBlogNavService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

