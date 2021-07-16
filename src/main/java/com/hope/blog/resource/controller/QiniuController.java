package com.hope.blog.resource.controller;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.resource.model.QiniuConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 七牛云文件存储 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Api(tags = "七牛云文件存储")
@RestController
@RequestMapping("/resource/qiniu")
public class QiniuController {

    @Autowired
    private QiniuService iqiniuService;

    /**
     * 查询七牛云配置
     */
    @ApiOperation(value = "查询七牛云配置")
    @PostMapping(value = "/findConfig")
    public CommonResult<QiniuConfig> findConfig() {
        QiniuConfig config = iqiniuService.findConfig();
        return CommonResult.success(config);
    }


    /**
     * 修改七牛云配置
     */
    @ApiOperation(value = "修改七牛云配置")
    @PostMapping(value = "/updateConfig")
    public CommonResult<ResultCode> updateConfig(@ApiParam @RequestBody QiniuConfig entity) {
        boolean success = iqiniuService.updateConfig(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 文件上传接口
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/file")
    public CommonResult<QiniuContent> uploadPic(@RequestParam("file") MultipartFile file) {
        QiniuContent qiniuContent = iqiniuService.uploadPhoto(file);
        return CommonResult.success(qiniuContent);
    }

    /**
     * 文件上传接口
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/files")
    public CommonResult<List<QiniuContent>> uploadPics(HttpServletRequest request) {
        List<QiniuContent> qiniuContent = iqiniuService.uploadPhotos(request);
        return CommonResult.success(qiniuContent);
    }
}

