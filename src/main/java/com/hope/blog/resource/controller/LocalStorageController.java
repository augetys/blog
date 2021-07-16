package com.hope.blog.resource.controller;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.service.LocalStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * <p>
 * 本地存储 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Api(tags = "本地存储")
@RestController
@RequestMapping("/resource/local")
public class LocalStorageController {

    @Autowired
    private LocalStorageService iLocalStorageService;

    /**
     * 文件上传接口
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/file")
    public CommonResult<LocalStorage> uploadPic(@RequestParam("file") MultipartFile file) {
        LocalStorage localStorage = iLocalStorageService.uploadPhoto(file);
        return CommonResult.success(localStorage);
    }

    /**
     * 多文件上传接口
     */
    @ApiOperation(value = "多文件上传接口")
    @PostMapping("/files")
    public CommonResult<List<LocalStorage>> uploadPics(HttpServletRequest request) {
        List<LocalStorage> filesList = iLocalStorageService.uploadPhotos(request);
        return CommonResult.success(filesList);
    }
}

