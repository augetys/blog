package com.hope.blog.comment.controller;

import com.hope.blog.comment.model.BlogCommentReplay;
import com.hope.blog.comment.service.BlogCommentReplayService;
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

    @Resource
    private BlogCommentReplayService iBlogCommentReplayService;

    /**
     * 回复
     */
    @ApiOperation(value = "回复")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid BlogCommentReplay entity){
        boolean success = iBlogCommentReplayService.commitReplay(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

}

