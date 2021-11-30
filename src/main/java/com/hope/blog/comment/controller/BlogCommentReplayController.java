package com.hope.blog.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.comment.model.BlogCommentReplay;
import com.hope.blog.comment.service.BlogCommentReplayService;
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
 * 博客文章评论回复表（子评论表） 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Api(tags = "博客文章评论回复表（子评论表）")
@RestController
@RequestMapping("/comment/blogCommentReplay")
public class BlogCommentReplayController {

    @Autowired
    private BlogCommentReplayService iBlogCommentReplayService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogCommentReplay>> findListByPage(@ApiParam @RequestBody BlogCommentReplay entity){
        IPage<BlogCommentReplay> list = iBlogCommentReplayService.findListByPage(entity);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogCommentReplay> getById(@PathVariable String id){
        BlogCommentReplay entity = iBlogCommentReplayService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody BlogCommentReplay entity){
        boolean success = iBlogCommentReplayService.saveOrUpdate(entity);
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
    public CommonResult<ResultCode> delete(@PathVariable String id){
        boolean success = iBlogCommentReplayService.removeById(id);
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
    public CommonResult<ResultCode> update(@ApiParam @RequestBody BlogCommentReplay entity){
        boolean success = iBlogCommentReplayService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

