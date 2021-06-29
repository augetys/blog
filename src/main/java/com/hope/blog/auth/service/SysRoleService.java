package com.hope.blog.auth.service;

import com.hope.blog.auth.dto.request.SysAllocMenusRequestDto;
import com.hope.blog.auth.dto.request.RoleSearchRequestDto;
import com.hope.blog.auth.dto.response.SysMenusResponseDto;
import com.hope.blog.auth.dto.response.SysRoleResponseDto;
import com.hope.blog.auth.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 分配菜单
     * @param sysAllocMenusRequestDto
     * @return
     */
    boolean allocMenu(SysAllocMenusRequestDto sysAllocMenusRequestDto);

    /**
     * 角色对应的菜单列表
     * @param roleId
     * @return
     */
    List<SysMenusResponseDto> listMenu(String roleId);

    /**
     * 分页查询角色列表
     * @param roleSearchRequestDto
     * @return
     */
    List<SysRoleResponseDto> findListByPage(RoleSearchRequestDto roleSearchRequestDto);
}
