package com.hope.blog.sys.mapper;

import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.model.SysRoleMenus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台角色与权限关联表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
public interface SysRoleMenusMapper extends BaseMapper<SysRoleMenus> {

    List<SysMenus> getMenuListByRoleId(String roleId);
}

