package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysMenusSearchRequestDto;
import com.hope.blog.sys.dto.response.SysMenusTreeResponseDto;
import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.mapper.SysMenusMapper;
import com.hope.blog.sys.service.SysMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
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

    @Resource
    private SysMenusMapper sysMenusMapper;

    @Override
    public IPage<SysMenus> findListByPage(SysMenusSearchRequestDto sysMenusSearchRequestDto) {
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
        return sysMenusMapper.selectPage(new Page<>(sysMenusSearchRequestDto.getPageNum(), sysMenusSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<SysMenusTreeResponseDto> treeList() {
        List<SysMenus> menuList = list();
        // 先查出一级菜单，先查询对应的子菜单
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals("0")).sorted(Comparator.comparing(SysMenus::getSort))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
    }

    /**
     * 菜单转树结构
     */
    private SysMenusTreeResponseDto covertMenuNode(SysMenus menu, List<SysMenus> menuList) {
        SysMenusTreeResponseDto node = new SysMenusTreeResponseDto();
        BeanUtils.copyProperties(menu, node);
        List<SysMenusTreeResponseDto> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId())).sorted(Comparator.comparing(SysMenus::getSort))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
