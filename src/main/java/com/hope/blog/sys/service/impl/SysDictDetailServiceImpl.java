package com.hope.blog.sys.service.impl;

import com.hope.blog.sys.dto.request.UpdateDictDetailStatusRequetDto;
import com.hope.blog.sys.model.SysDictDetail;
import com.hope.blog.sys.mapper.SysDictDetailMapper;
import com.hope.blog.sys.service.SysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public boolean updateStatusById(UpdateDictDetailStatusRequetDto updateDictDetailStatusRequetDto) {
        return sysDictDetailMapper.updateStatus(updateDictDetailStatusRequetDto)>0;
    }
}
