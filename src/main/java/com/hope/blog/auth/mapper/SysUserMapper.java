package com.hope.blog.auth.mapper;

import com.hope.blog.auth.dto.request.UpdateSysUserStatusRequestDto;
import com.hope.blog.auth.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hope.blog.auth.model.SysMenus;
import com.hope.blog.auth.model.SysRole;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    List<SysMenus> getMenus(String userId);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> getRoles(String userId);

    /**
     * 修改用户状态
     * @param updateSysUserStatusRequestDto
     * @return
     */
    int updateStatus(UpdateSysUserStatusRequestDto updateSysUserStatusRequestDto);
}
