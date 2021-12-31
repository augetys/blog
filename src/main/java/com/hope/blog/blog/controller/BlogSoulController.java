package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogSoulSearchRequest;
import com.hope.blog.blog.model.BlogSoul;
import com.hope.blog.blog.service.BlogSoulService;
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
 * 心灵鸡汤表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
@Api(tags = "心灵鸡汤表")
@RestController
@RequestMapping("/blog/soul")
public class BlogSoulController {

    @Resource
    private BlogSoulService iBlogSoulService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogSoul>> findListByPage(@ApiParam @RequestBody BlogSoulSearchRequest blogSoulSearchRequest){
        IPage<BlogSoul> list = iBlogSoulService.findListByPage(blogSoulSearchRequest);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 随机获取词句
     */
    @ApiOperation(value = "随机获取词句")
    @PostMapping(value = "/random")
    public CommonResult<List<BlogSoul>> random(){
        List<BlogSoul> list = iBlogSoulService.random();
        return CommonResult.success(list);
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogSoul> getById(@PathVariable String id){
        BlogSoul entity = iBlogSoulService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增词句")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid BlogSoul entity){
        boolean success = iBlogSoulService.saveOrUpdate(entity);
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
    @OperationLog(value = "删除词句（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id){
        boolean success = iBlogSoulService.removeById(id);
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
    @OperationLog(value = "修改词句")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid BlogSoul entity){
        boolean success = iBlogSoulService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

