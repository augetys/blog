package com.hope.blog.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.sys.dto.request.SysMenusSearchRequestDto;
import com.hope.blog.sys.dto.response.SysMenusTreeResponseDto;
import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.service.SysMenusService;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 后台用户菜单表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Api(tags = "后台用户菜单表")
@RestController
@RequestMapping("/sys/menus")
public class SysMenusController {

    @Resource
    private SysMenusService sysMenusService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysMenus>> findListByPage(@ApiParam @RequestBody SysMenusSearchRequestDto sysMenusSearchRequestDto) {
        IPage<SysMenus> list = sysMenusService.findListByPage(sysMenusSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<SysMenus> getById(@PathVariable String id) {
        SysMenus entity = sysMenusService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增菜单")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid SysMenus entity) {
        boolean success = sysMenusService.saveOrUpdate(entity);
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
    @OperationLog(value = "删除菜单")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = sysMenusService.removeById(id);
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
    @OperationLog(value = "修改菜单信息")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid SysMenus entity) {
        boolean success = sysMenusService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 菜单树
     */
    @ApiOperation(value = "菜单树")
    @GetMapping(value = "/treeList")
    public CommonResult<List<SysMenusTreeResponseDto>> treeList() {
        List<SysMenusTreeResponseDto> sysMenusTreeResponseDtos = sysMenusService.treeList();
        return CommonResult.success(sysMenusTreeResponseDtos);
    }
}

