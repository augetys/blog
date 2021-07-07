package com.hope.blog.resource.controller;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 文件上传接口
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/file")
    public CommonResult<QiniuContent> uploadPics(@RequestParam("file") MultipartFile file) {
        QiniuContent qiniuContent = iqiniuService.uploadPhoto(file);
        return CommonResult.success(qiniuContent);
    }
}

