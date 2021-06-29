package com.hope.blog.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.auth.dto.request.SysMenusSearchRequestDto;
import com.hope.blog.auth.dto.response.SysMenusResponseDto;
import com.hope.blog.auth.dto.response.SysMenusTreeResponseDto;
import com.hope.blog.auth.model.SysMenus;
import com.hope.blog.auth.mapper.SysMenusMapper;
import com.hope.blog.auth.service.SysMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户菜单表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Service
@Transactional
public class SysMenusServiceImpl extends ServiceImpl<SysMenusMapper, SysMenus> implements SysMenusService {

    @Autowired
    private SysMenusMapper sysMenusMapper;

    @Override
    public List<SysMenusResponseDto> findListByPage(SysMenusSearchRequestDto sysMenusSearchRequestDto) {
        QueryWrapper<SysMenus> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysMenusSearchRequestDto.getName())) {
            queryWrapper.like("name", sysMenusSearchRequestDto.getName());
        }
        if (!StringUtils.isEmpty(sysMenusSearchRequestDto.getParentId())) {
            queryWrapper.eq("parent_id", sysMenusSearchRequestDto.getParentId());
        }
        if (!StringUtils.isEmpty(sysMenusSearchRequestDto.getLevel())) {
            queryWrapper.eq("level", sysMenusSearchRequestDto.getLevel());
        }
        queryWrapper.lambda().orderByAsc(SysMenus::getSort);
        List<SysMenus> records = sysMenusMapper.selectPage(new Page<>(sysMenusSearchRequestDto.getPageNum(), sysMenusSearchRequestDto.getPageSize()), queryWrapper).getRecords();
        return CopyUtil.copyList(records, SysMenusResponseDto.class);
    }

    @Override
    public List<SysMenusTreeResponseDto> treeList() {
        List<SysMenus> menuList = list();
        // 先查出一级菜单，先查询对应的子菜单
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals("0"))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
    }

    /**
     * 菜单转树结构
     */
    private SysMenusTreeResponseDto covertMenuNode(SysMenus menu, List<SysMenus> menuList) {
        SysMenusTreeResponseDto node = new SysMenusTreeResponseDto();
        BeanUtils.copyProperties(menu, node);
        List<SysMenusTreeResponseDto> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
