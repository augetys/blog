package com.hope.blog.sys.mapper;

import com.hope.blog.sys.model.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取用户的角色信息
     * @param userId
     * @return
     */
    List<SysRole> getRoleList(String userId);
}
