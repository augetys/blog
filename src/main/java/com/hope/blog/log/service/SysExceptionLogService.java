package com.hope.blog.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.log.dto.request.SysExceptionLogSearchRequestDto;
import com.hope.blog.log.model.SysExceptionLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 异常日志表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-06
 */
public interface SysExceptionLogService extends IService<SysExceptionLog> {
    IPage<SysExceptionLog> findListByPage(SysExceptionLogSearchRequestDto sysExceptionLogSearchRequestDto);
}
