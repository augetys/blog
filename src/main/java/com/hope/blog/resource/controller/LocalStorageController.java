package com.hope.blog.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.service.LocalStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Resource
    private LocalStorageService iLocalStorageService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<LocalStorage>> findListByPage(@ApiParam @RequestBody FileSearchRequestDto fileSearchRequestDto) {
        IPage<LocalStorage> list = iLocalStorageService.findListByPage(fileSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delete/{id}")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iLocalStorageService.deleteById(id);
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
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid LocalStorage localStorage) {
        boolean success = iLocalStorageService.updateById(localStorage);
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
    @OperationLog(value = "本地文件上传")
    public CommonResult<LocalStorage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        LocalStorage localStorage = iLocalStorageService.uploadFile(file, name);
        return CommonResult.success(localStorage);
    }

    /**
     * 多文件上传接口
     */
    @ApiOperation(value = "多文件上传接口")
    @PostMapping("/files")
    @OperationLog(value = "本地多文件上传")
    public CommonResult<List<LocalStorage>> uploadFiles(HttpServletRequest request) {
        List<LocalStorage> filesList = iLocalStorageService.uploadFiles(request);
        return CommonResult.success(filesList);
    }
}

