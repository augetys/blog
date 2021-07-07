package com.hope.blog.sys.controller;

import com.hope.blog.sys.model.ConfigQiniu;
import com.hope.blog.sys.service.ConfigQiniuService;
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
 * 七牛云配置 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Api(tags = "七牛云配置")
@RestController
@RequestMapping("/sys/configQiniu")
    public class ConfigQiniuController {

    @Autowired
    private ConfigQiniuService iConfigQiniuService;

     /**
     * 修改单条记录
     */
     @ApiOperation(value = "修改单条记录")
     @PostMapping(value = "/update")
     public CommonResult<ResultCode> update(@ApiParam @RequestBody ConfigQiniu entity){
        boolean success=iConfigQiniuService.updateById(entity);
        if (success){
        return CommonResult.success();
        }
        return CommonResult.failed();
     }
}

