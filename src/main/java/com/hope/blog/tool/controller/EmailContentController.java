package com.hope.blog.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.tool.dto.request.EmailSendRequestDto;
import com.hope.blog.tool.model.EmailContent;
import com.hope.blog.tool.service.EmailContentService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 邮箱发送内容 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-16
 */
@Api(tags = "邮箱发送内容")
@RestController
@RequestMapping("/tool/emailContent")
public class EmailContentController {

    @Resource
    private EmailContentService iEmailContentService;

    /**
     * 发送邮件
     */
    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/send")
    public CommonResult<ResultCode> send(@ApiParam @RequestBody EmailSendRequestDto emailSendRequestDto) {
        boolean success = iEmailContentService.send(emailSendRequestDto);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

