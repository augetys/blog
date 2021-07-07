package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.mapper.SysDictMapper;
import com.hope.blog.sys.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Service
@Transactional
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public Page<SysDict> findListByPage(SysDictSearchRequestDto sysDictSearchRequestDto) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysDictSearchRequestDto.getName())) {
            queryWrapper.like("name", sysDictSearchRequestDto.getName());
        }
        queryWrapper.lambda().orderByAsc(SysDict::getSort);
        return sysDictMapper.selectPage(new Page<>(sysDictSearchRequestDto.getPageNum(), sysDictSearchRequestDto.getPageSize()), queryWrapper);
    }
}