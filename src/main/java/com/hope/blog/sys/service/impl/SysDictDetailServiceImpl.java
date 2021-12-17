package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.sys.dto.request.UpdateDictDetailStatusRequetDto;
import com.hope.blog.sys.mapper.SysDictMapper;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.mapper.SysDictDetailMapper;
import com.hope.blog.sys.service.SysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Service
@Transactional
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService {

    @Autowired
    private SysDictDetailMapper sysDictDetailMapper;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public boolean updateStatusById(UpdateDictDetailStatusRequetDto updateDictDetailStatusRequetDto) {
        return sysDictDetailMapper.updateStatus(updateDictDetailStatusRequetDto) > 0;
    }

    @Override
    public List<SysDictDetail> findByDictName(String name) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.eq("name", name);
        }
        SysDict sysDict = sysDictMapper.selectOne(queryWrapper);
        QueryWrapper<SysDictDetail> queryWrapper1 = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysDict.getId())) {
            queryWrapper1.eq("dict_id", sysDict.getId());
        }
        queryWrapper1.orderByAsc("sort");
        return sysDictDetailMapper.selectList(queryWrapper1);
    }
}
