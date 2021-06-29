package com.hope.blog.log.service;

import com.hope.blog.log.dto.request.SysLogListRequestDto;
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
    List<SysLog> findListByPage(SysLogListRequestDto sysLog);
}
