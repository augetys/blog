package com.hope.blog.auth.service.impl;

import com.hope.blog.auth.model.SysRoleMenus;
import com.hope.blog.auth.mapper.SysRoleMenusMapper;
import com.hope.blog.auth.service.SysRoleMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台角色与权限关联表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Service
@Transactional
public class SysRoleMenusServiceImpl extends ServiceImpl<SysRoleMenusMapper, SysRoleMenus> implements SysRoleMenusService {

}
