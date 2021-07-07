package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.sys.mapper.SysUserMenusMapper;
import com.hope.blog.sys.model.SysUserMenus;
import com.hope.blog.sys.service.SysUserMenusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台用户与权限附加表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Service
@Transactional
public class SysUserMenusServiceImpl extends ServiceImpl<SysUserMenusMapper, SysUserMenus> implements SysUserMenusService {

}
