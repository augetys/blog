package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogWebsiteSearchRequest;
import com.hope.blog.blog.dto.response.BlogWebsiteListResponse;
import com.hope.blog.blog.dto.response.BlogWebsiteResponse;
import com.hope.blog.blog.model.BlogWebsite;
import com.hope.blog.blog.service.BlogWebsiteService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网址导航表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-12-29
 */
@Api(tags = "网址导航表")
@RestController
@RequestMapping("/blog/website")
public class BlogWebsiteController {

    @Resource
    private BlogWebsiteService iBlogWebsiteService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogWebsiteResponse>> findListByPage(@ApiParam @RequestBody BlogWebsiteSearchRequest BlogWebsiteSearchRequest) {
        IPage<BlogWebsiteResponse> list = iBlogWebsiteService.findListByPage(BlogWebsiteSearchRequest);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogWebsite> getById(@PathVariable String id) {
        BlogWebsite entity = iBlogWebsiteService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增网址导航")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid BlogWebsite entity) {
        boolean success = iBlogWebsiteService.saveOrUpdate(entity);
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
    @OperationLog(value = "删除网址导航（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iBlogWebsiteService.removeById(id);
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
    @OperationLog(value = "修改网址导航")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid BlogWebsite entity) {
        boolean success = iBlogWebsiteService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 查询所有类别的网址导航
     */
    @ApiOperation(value = "查询所有类别的网址导航")
    @PostMapping(value = "/getNavigation")
    public CommonResult<BlogWebsiteListResponse> getNavigation() {
        BlogWebsiteListResponse BlogWebsiteListResponse = iBlogWebsiteService.getNavigation();
        return CommonResult.success(BlogWebsiteListResponse);
    }
}

