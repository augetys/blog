package com.hope.blog.sys.service;

import com.hope.blog.sys.dto.request.UpdateDictDetailStatusRequetDto;
import com.hope.blog.sys.model.SysDictDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface SysDictDetailService extends IService<SysDictDetail> {
    boolean updateStatusById(UpdateDictDetailStatusRequetDto updateDictDetailStatusRequetDto);
}
