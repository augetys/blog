package com.hope.blog.auth.controller;

import com.hope.blog.auth.dto.request.SysAllocMenusRequestDto;
import com.hope.blog.auth.dto.request.RoleSearchRequestDto;
import com.hope.blog.auth.dto.response.SysMenusResponseDto;
import com.hope.blog.auth.dto.response.SysRoleResponseDto;
import com.hope.blog.auth.model.SysRole;
import com.hope.blog.auth.service.SysRoleService;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.utils.CopyUtil;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Api(tags = "后台用户角色表")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    
    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysRoleResponseDto>> findListByPage(@ApiParam @RequestBody RoleSearchRequestDto roleSearchRequestDto) {
        List<SysRoleResponseDto> list = sysRoleService.findListByPage(roleSearchRequestDto);

        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<SysRoleResponseDto> getById(@PathVariable String id) {
        SysRole entity = sysRoleService.getById(id);
        return CommonResult.success(CopyUtil.copy(entity,SysRoleResponseDto.class));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增角色")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody SysRole entity) {
        boolean success = sysRoleService.saveOrUpdate(entity);
        if (success){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除单条记录
     */
    @ApiOperation(value = "删除单条记录")
    @GetMapping(value = "/delete/{id}")
    @OperationLog(value = "删除角色")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = sysRoleService.removeById(id);
        if (success){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改单条记录
     */
    @ApiOperation(value = "修改单条记录")
    @PostMapping(value = "/update")
    @OperationLog(value = "修改角色信息")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody SysRole entity) {
        boolean success = sysRoleService.updateById(entity);
        if (success){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色相关菜单")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SysMenusResponseDto>> listResource(@PathVariable String roleId) {
        List<SysMenusResponseDto> roleList = sysRoleService.listMenu(roleId);
        return CommonResult.success(roleList);
    }

    /**
     * 分配菜单
     */
    @ApiOperation(value = "分配菜单")
    @PostMapping(value = "/allocMenu")
    @OperationLog(value = "分配菜单")
    public CommonResult<ResultCode> allocMenu(@ApiParam @RequestBody SysAllocMenusRequestDto sysAllocMenusRequestDto) {
        boolean success = sysRoleService.allocMenu(sysAllocMenusRequestDto);
        if (success){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

}

