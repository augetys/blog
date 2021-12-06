package com.hope.blog.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.log.dto.request.SysLogSearchRequestDto;
import com.hope.blog.log.model.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-06-22
 */
public interface SysLogService extends IService<SysLog> {
    IPage<SysLog> findListByPage(SysLogSearchRequestDto sysLog);
}
