package com.hope.blog.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.LoginRequestDto;
import com.hope.blog.sys.dto.request.RegisterRequestDto;
import com.hope.blog.sys.dto.request.UpdateSysUserStatusRequestDto;
import com.hope.blog.sys.dto.request.SysUserSearchRequestDto;
import com.hope.blog.sys.dto.response.SysUserInfoResponseDto;
import com.hope.blog.sys.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.model.SysRole;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    SysUser findUserByUserName(String username);

    /**
     * 登录获取token
     *
     * @param loginRequestDto
     * @return
     */
    String login(LoginRequestDto loginRequestDto);

    /**
     * 注册用户
     *
     * @param registerRequestDto
     * @return
     */
    SysUser register(RegisterRequestDto registerRequestDto);

    /**
     * 获取当前登录用户信息
     *
     * @param principal
     * @return
     */
    SysUserInfoResponseDto getUserInfo(Principal principal);

    /**
     * 退出
     *
     * @return
     */
    SysUser logout();

    /**
     * 根据用户id查询所拥有的角色信息
     *
     * @param AdminId
     * @return
     */
    List<SysRole> getRoles(String AdminId);

    /**
     * 根据用户id查询所拥有的权限信息
     *
     * @param AdminId
     * @return
     */
    List<SysMenus> getAuthority(String AdminId);

    /**
     * 修改用户状态
     *
     * @param updateSysUserStatusRequestDto
     * @return
     */
    boolean updateStatusRequest(UpdateSysUserStatusRequestDto updateSysUserStatusRequestDto);

    /**
     * 获取用户角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getRoleList(String userId);

    /**
     * 分配角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int updateRole(String userId, List<String> roleIds);

    /**
     * 注销用户
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 分页查询用户列表
     *
     * @param sysUserSearchRequestDto
     * @return
     */
    Page<SysUser> findListByPage(SysUserSearchRequestDto sysUserSearchRequestDto);

}
