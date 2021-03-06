package com.hope.blog.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.sys.dto.request.LoginRequestDto;
import com.hope.blog.sys.dto.request.RegisterRequestDto;
import com.hope.blog.sys.dto.request.UpdateSysUserStatusRequestDto;
import com.hope.blog.sys.dto.request.SysUserSearchRequestDto;
import com.hope.blog.sys.dto.response.SysUserInfoResponseDto;
import com.hope.blog.sys.model.SysRole;
import com.hope.blog.sys.model.SysUser;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
@Api(tags = "后台用户表")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService iSysUserService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用户列表
     */
    @ApiOperation(value = "用户列表")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<SysUser>> findListByPage(@ApiParam @RequestBody SysUserSearchRequestDto sysUserSearchRequestDto) {
        IPage<SysUser> list = iSysUserService.findListByPage(sysUserSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询用户")
    @GetMapping(value = "/{id}")
    public CommonResult<SysUser> getById(@PathVariable String id) {
        SysUser entity = iSysUserService.getById(id);
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/create")
    @OperationLog(value = "新增用户")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid SysUser sysUser) {
        // 默认头像
        String encodePassword = passwordEncoder.encode(CommonConstant.PASSWORD);
        sysUser.setPassword(encodePassword);
        // 默认头像
        sysUser.setIcon(CommonConstant.USERAVATAR);
        boolean success = iSysUserService.saveOrUpdate(sysUser);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除用户信息
     */
    @ApiOperation(value = "删除用户信息")
    @GetMapping(value = "/delete/{id}")
    @OperationLog(value = "删除用户")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iSysUserService.deleteById(id);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改用户信息
     */
    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/update")
    @OperationLog(value = "修改用户信息")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid SysUser sysUser) {
        boolean success = iSysUserService.updateById(sysUser);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public CommonResult<String> login(@ApiParam @RequestBody @Valid LoginRequestDto loginRequestDto) {
        String token = iSysUserService.login(loginRequestDto);
        return CommonResult.success(token);
    }

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public CommonResult<SysUser> register(@ApiParam @RequestBody RegisterRequestDto registerRequestDto) {
        SysUser sysUser = iSysUserService.register(registerRequestDto);
        return CommonResult.success(sysUser);
    }

    /**
     * 获取当前登录用户信息
     */
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public CommonResult<SysUserInfoResponseDto> getUserInfo(Principal principal) {
        SysUserInfoResponseDto sysUserInfoResponseDto = iSysUserService.getUserInfo(principal);
        return CommonResult.success(sysUserInfoResponseDto);
    }

    /**
     * 退出
     */
    @ApiOperation(value = "退出")
    @GetMapping(value = "/logout")
    public CommonResult<SysUser> logout() {
        SysUser sysUser = iSysUserService.logout();
        return CommonResult.success(sysUser);
    }

    /**
     * 修改用户状态
     */
    @ApiOperation(value = "修改用户状态")
    @PostMapping(value = "/updateStatus")
    @OperationLog(value = "修改用户状态")
    public CommonResult<ResultCode> updateStatus(@ApiParam @RequestBody @Valid UpdateSysUserStatusRequestDto updateSysUserStatusRequestDto) {
        boolean success = iSysUserService.updateStatusRequest(updateSysUserStatusRequestDto);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 获取用户角色信息
     */
    @ApiOperation(value = "获取用户角色信息")
    @GetMapping(value = "/role/{userId}")
    public CommonResult<List<SysRole>> getRoleList(@ApiParam @PathVariable String userId) {
        List<SysRole> roleList = iSysUserService.getRoleList(userId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    @OperationLog(value = "给用户分配角色")
    public CommonResult<ResultCode> updateRole(@RequestParam("userId") String userId,
                                               @RequestParam("roleIds") List<String> roleIds) {
        int count = iSysUserService.updateRole(userId, roleIds);
        if (count >= 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}

