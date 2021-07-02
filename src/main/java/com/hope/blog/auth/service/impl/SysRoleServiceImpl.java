package com.hope.blog.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.auth.dto.request.SysAllocMenusRequestDto;
import com.hope.blog.auth.dto.request.RoleSearchRequestDto;
import com.hope.blog.auth.mapper.SysRoleMenusMapper;
import com.hope.blog.auth.model.SysMenus;
import com.hope.blog.auth.model.SysRole;
import com.hope.blog.auth.mapper.SysRoleMapper;
import com.hope.blog.auth.model.SysRoleMenus;
import com.hope.blog.auth.service.SysRoleMenusService;
import com.hope.blog.auth.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenusService sysRoleMenusService;

    @Autowired
    private SysRoleMenusMapper sysRoleMenusMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<SysRole> findListByPage(RoleSearchRequestDto roleSearchRequestDto) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = roleSearchRequestDto.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        return sysRoleMapper.selectPage(new Page<>(roleSearchRequestDto.getPageNum(), roleSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<SysMenus> listMenu(String roleId) {
        return sysRoleMenusMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public boolean allocMenu(SysAllocMenusRequestDto sysAllocMenusRequestDto) {
        // 先删除原有关系
        QueryWrapper<SysRoleMenus> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysRoleMenus::getRoleId, sysAllocMenusRequestDto.getRoleId());
        sysRoleMenusService.remove(wrapper);
        // 插入新菜单
        List<String> menuIds = sysAllocMenusRequestDto.getMenuIds();
        List<SysRoleMenus> sysRoleMenusList = new ArrayList<>();
        for (String menuId : menuIds) {
            SysRoleMenus sysRoleMenus = new SysRoleMenus();
            sysRoleMenus.setRoleId(sysAllocMenusRequestDto.getRoleId());
            sysRoleMenus.setMenuId(menuId);
            sysRoleMenusList.add(sysRoleMenus);
        }
        return sysRoleMenusService.saveOrUpdateBatch(sysRoleMenusList);
    }
}
