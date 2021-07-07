package com.hope.blog.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysMenusSearchRequestDto;
import com.hope.blog.sys.dto.response.SysMenusTreeResponseDto;
import com.hope.blog.sys.model.SysMenus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户菜单表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
public interface SysMenusService extends IService<SysMenus> {

    Page<SysMenus> findListByPage(SysMenusSearchRequestDto sysMenusSearchRequestDto);

    List<SysMenusTreeResponseDto> treeList();
}
