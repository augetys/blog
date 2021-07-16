package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysDictDetailSearchRequestDto;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.mapper.SysDictDetailMapper;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.mapper.SysDictMapper;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Autowired
    private SysDictDetailMapper sysDictDetailMapper;

    @Override
    public Page<SysDict> findListByPage(SysDictSearchRequestDto sysDictSearchRequestDto) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysDictSearchRequestDto.getName())) {
            queryWrapper.like("name", sysDictSearchRequestDto.getName());
        }
        return sysDictMapper.selectPage(new Page<>(sysDictSearchRequestDto.getPageNum(), sysDictSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<SysDictDetail> getDetailById(SysDictDetailSearchRequestDto sysDictDetailSearchRequestDto) {
        QueryWrapper<SysDictDetail> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysDictDetailSearchRequestDto.getDictId())) {
            queryWrapper.eq("dict_id", sysDictDetailSearchRequestDto.getDictId());
        }
        if (!StringUtils.isEmpty(sysDictDetailSearchRequestDto.getLabel())) {
            queryWrapper.eq("label", sysDictDetailSearchRequestDto.getLabel());
        }
        queryWrapper.lambda().orderByAsc(SysDictDetail::getSort);
        return sysDictDetailMapper.selectList(queryWrapper);
    }
}
