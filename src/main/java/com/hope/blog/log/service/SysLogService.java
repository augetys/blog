package com.hope.blog.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.log.dto.request.SysLogSearchRequestDto;
import com.hope.blog.log.model.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-06-22
 */
public interface SysLogService extends IService<SysLog> {
    Page<SysLog> findListByPage(SysLogSearchRequestDto sysLog);
}
