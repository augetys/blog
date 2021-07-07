package com.hope.blog.sys.service.impl;

import com.hope.blog.sys.mapper.SysUserRoleMapper;
import com.hope.blog.sys.model.SysUserRole;
import com.hope.blog.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台用户与角色关联表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
