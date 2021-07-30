package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogLoopSearchRequestDto;
import com.hope.blog.blog.model.BlogLoop;
import com.hope.blog.blog.service.BlogLoopService;
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
 * 轮播图 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/blog/loop")
public class BlogLoopController {

    @Autowired
    private BlogLoopService iBlogLoopService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogLoop>> findListByPage(@ApiParam @RequestBody BlogLoopSearchRequestDto blogLoopSearchRequestDto) {
        Page<BlogLoop> list = iBlogLoopService.findListByPage(blogLoopSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/findAll")
    public CommonResult<List<BlogLoop>> findAll() {
        List<BlogLoop> list = iBlogLoopService.findAll();
        return CommonResult.success(list);
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogLoop> getById(@PathVariable String id) {
        BlogLoop entity = iBlogLoopService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogLoop entity) {
        boolean success = iBlogLoopService.saveOrUpdate(entity);
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
        boolean success = iBlogLoopService.removeById(id);
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
    public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogLoop entity) {
        boolean success = iBlogLoopService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

