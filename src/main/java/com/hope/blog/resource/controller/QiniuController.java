package com.hope.blog.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
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
    public CommonResult<List<QiniuConfig>> findConfig() {
        List<QiniuConfig> config = iqiniuService.findConfig();
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
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<QiniuContent>> findListByPage(@ApiParam @RequestBody FileSearchRequestDto fileSearchRequestDto) {
        IPage<QiniuContent> list = iqiniuService.findListByPage(fileSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 文件上传接口
     */
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/file")
    public CommonResult<QiniuContent> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucket") String bucket, @RequestParam("name") String name) {
        QiniuContent qiniuContent = iqiniuService.uploadFile(file, bucket, name);
        return CommonResult.success(qiniuContent);
    }

    /**
     * 文件上传接口
     */
    @ApiOperation(value = "多文件上传接口")
    @PostMapping("/files")
    public CommonResult<List<QiniuContent>> uploadFiles(HttpServletRequest request, @RequestParam("bucket") String bucket) {
        List<QiniuContent> qiniuContent = iqiniuService.uploadFiles(request, bucket);
        return CommonResult.success(qiniuContent);
    }

    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delete/{id}")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iqiniuService.deleteById(id);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

