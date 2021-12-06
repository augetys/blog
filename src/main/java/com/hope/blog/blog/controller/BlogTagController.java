package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogTagSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogTagListResponse;
import com.hope.blog.blog.model.BlogTag;
import com.hope.blog.blog.service.BlogTagService;
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
 * 博客标签表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Api(tags = "博客标签表")
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService iBlogTagService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogTag>> findListByPage(@ApiParam @RequestBody BlogTagSearchRequestDto blogTagSearchRequestDto) {
        IPage<BlogTag> list = iBlogTagService.findListByPage(blogTagSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 查询所有数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/findAll")
    public CommonResult<List<BlogTag>> findAll(@ApiParam @RequestBody BlogTagSearchRequestDto blogTagSearchRequestDto) {
        List<BlogTag> list = iBlogTagService.findAll(blogTagSearchRequestDto);
        return CommonResult.success(list);
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogTag> getById(@PathVariable String id) {
        BlogTag entity = iBlogTagService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogTag entity) {
        boolean success = iBlogTagService.saveOrUpdate(entity);
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
        boolean success = iBlogTagService.removeById(id);
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
    public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogTag entity) {
        boolean success = iBlogTagService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }


    // 门户
    /**
     * 查询标签
     */
    @ApiOperation(value = "查询标签")
    @GetMapping(value = "/getTag")
    public CommonResult<List<BlogTagListResponse>> getTag() {
        List<BlogTagListResponse> list = iBlogTagService.getTag();
        return CommonResult.success(list);
    }
}

