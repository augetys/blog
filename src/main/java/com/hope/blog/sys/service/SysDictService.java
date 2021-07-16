package com.hope.blog.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysDictDetailSearchRequestDto;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.model.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.blog.sys.model.SysDictDetail;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface SysDictService extends IService<SysDict> {
    Page<SysDict> findListByPage(SysDictSearchRequestDto sysDictSearchRequestDto);

    List<SysDictDetail> getDetailById(SysDictDetailSearchRequestDto sysDictDetailSearchRequestDto);
}
