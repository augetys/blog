package com.hope.blog.sys.controller;

import com.hope.blog.sys.dto.request.EmailSendRequestDto;
import com.hope.blog.sys.model.ConfigEmail;
import com.hope.blog.sys.service.ConfigEmailService;
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
 * 邮箱配置 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Api(tags = "邮箱配置")
@RestController
@RequestMapping("/sys/configEmail")
    public class ConfigEmailController {

    @Autowired
    private ConfigEmailService iConfigEmailService;

    /**
     * 查询邮箱配置
     */
    @ApiOperation(value = "查询邮箱配置")
    @GetMapping(value = "/find")
    public CommonResult<ConfigEmail> find(){
        ConfigEmail entity=iConfigEmailService.find();
        return CommonResult.success(entity);
    }

     /**
     * 修改单条记录
     */
     @ApiOperation(value = "修改配置")
     @PostMapping(value = "/update")
     public CommonResult<ResultCode> update(@ApiParam @RequestBody ConfigEmail entity){
        boolean success=iConfigEmailService.updateById(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
     }

    /**
     * 发送邮件
     */
    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/send")
    public CommonResult<ResultCode> send(@ApiParam @RequestBody EmailSendRequestDto emailSendRequestDto){
        boolean success=iConfigEmailService.send(emailSendRequestDto,iConfigEmailService.find());
        if (success){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

