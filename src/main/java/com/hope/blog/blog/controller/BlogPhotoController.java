package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogPhotoSearchRequest;
import com.hope.blog.blog.model.BlogPhoto;
import com.hope.blog.blog.service.BlogPhotoService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 相册 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
@Api(tags = "相册")
@RestController
@RequestMapping("/blog/photo")
public class BlogPhotoController {

    @Resource
    private BlogPhotoService iBlogPhotoService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogPhoto>> findListByPage(@ApiParam @RequestBody BlogPhotoSearchRequest blogPhotoSearchRequest){
        IPage<BlogPhoto> list = iBlogPhotoService.findListByPage(blogPhotoSearchRequest);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogPhoto> getById(@PathVariable String id){
        BlogPhoto entity = iBlogPhotoService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增相册")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid BlogPhoto entity){
        boolean success = iBlogPhotoService.saveOrUpdate(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除单条记录
     */
    @ApiOperation(value = "删除单条记录")
    @GetMapping(value = "/delete/{id}")
    @OperationLog(value = "删除相册（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id){
        boolean success = iBlogPhotoService.delete(id);
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
    @OperationLog(value = "修改相册")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid BlogPhoto entity){
        boolean success = iBlogPhotoService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 查询相册所有图片
     */
    @ApiOperation(value = "查询相册所有图片")
    @PostMapping(value = "/findAll")
    public CommonResult<List<BlogPhoto>> findAll(@ApiParam @RequestBody BlogPhotoSearchRequest blogPhotoSearchRequest){
        List<BlogPhoto> list = iBlogPhotoService.findAll(blogPhotoSearchRequest);
        return CommonResult.success(list);
    }
}

