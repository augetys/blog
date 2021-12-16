package com.hope.blog.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.comment.dto.request.BlogCommentQueryRequest;
import com.hope.blog.comment.dto.response.BlogCommentResponse;
import com.hope.blog.comment.model.BlogComment;
import com.hope.blog.comment.service.BlogCommentService;
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

import javax.validation.Valid;


/**
 * <p>
 * 博客文章评论表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Api(tags = "博客文章评论表")
@RestController
@RequestMapping("/comment/blogComment")
public class BlogCommentController {

    @Autowired
    private BlogCommentService iBlogCommentService;

    /**
     * 评论
     */
    @ApiOperation(value = "评论")
    @PostMapping(value = "/save")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid BlogComment entity){
        boolean success = iBlogCommentService.commitComment(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 根据文章id获取相关评论
     */
    @ApiOperation(value = "根据文章id获取相关评论")
    @PostMapping(value = "/commentsByArticleId")
    public CommonResult<CommonPage<BlogCommentResponse>> commentsByArticleId(@ApiParam @RequestBody BlogCommentQueryRequest blogCommentQueryRequest){
        IPage<BlogCommentResponse> list = iBlogCommentService.commentsByArticleId(blogCommentQueryRequest);
        return CommonResult.success(CommonPage.restPage(list));
    }
}

