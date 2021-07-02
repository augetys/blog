package com.hope.blog.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.auth.dto.request.SysAllocMenusRequestDto;
import com.hope.blog.auth.dto.request.RoleSearchRequestDto;
import com.hope.blog.auth.model.SysMenus;
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
    List<SysMenus> listMenu(String roleId);

    /**
     * 分页查询角色列表
     * @param roleSearchRequestDto
     * @return
     */
    Page<SysRole> findListByPage(RoleSearchRequestDto roleSearchRequestDto);
}
