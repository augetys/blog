package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.sys.dto.request.SysDictDetailSearchRequestDto;
import com.hope.blog.sys.dto.request.SysDictSearchRequestDto;
import com.hope.blog.sys.mapper.SysDictDetailMapper;
import com.hope.blog.sys.model.SysDict;
import com.hope.blog.sys.mapper.SysDictMapper;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.service.SysDictDetailService;
import com.hope.blog.sys.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private SysDictMapper sysDictMapper;

    @Resource
    private SysDictDetailMapper sysDictDetailMapper;

    @Resource
    private SysDictDetailService sysDictDetailService;

    @Override
    public IPage<SysDict> findListByPage(SysDictSearchRequestDto sysDictSearchRequestDto) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysDictSearchRequestDto.getName())) {
            queryWrapper.like("name", sysDictSearchRequestDto.getName());
        }
        queryWrapper.orderByDesc("create_time");
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
        queryWrapper.lambda().orderByDesc(SysDictDetail::getSort);
        return sysDictDetailMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, List<SysDictDetail>> getDetailByNames(List<String> nameLists) {
        HashMap<String, List<SysDictDetail>> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(nameLists)) {
            nameLists.forEach(
                    item -> {
                        resultMap.put(item,sysDictDetailService.findByDictName(item));
                    }
            );
        }
        return resultMap;
    }
}
