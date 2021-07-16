package com.hope.blog.sys.mapper;

import com.hope.blog.sys.dto.request.UpdateDictDetailStatusRequetDto;
import com.hope.blog.sys.model.SysDictDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 数据字典详情 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {

    int updateStatus(UpdateDictDetailStatusRequetDto updateDictDetailStatusRequetDto);

}
