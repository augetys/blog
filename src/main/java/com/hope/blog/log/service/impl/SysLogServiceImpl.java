package com.hope.blog.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.log.dto.request.SysLogSearchRequestDto;
import com.hope.blog.log.model.SysLog;
import com.hope.blog.log.mapper.SysLogMapper;
import com.hope.blog.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.DateUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-06-22
 */
@Service
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public IPage<SysLog> findListByPage(SysLogSearchRequestDto sysLog) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysLog.getOperation())) {
            queryWrapper.like("operation", sysLog.getOperation());
        }
        if (!StringUtils.isEmpty(sysLog.getUserName())) {
            queryWrapper.like("user_name", sysLog.getUserName());
        }
        if (!StringUtils.isEmpty(sysLog.getCreateTime())) {
            List<Date> startTime = sysLog.getCreateTime();
            queryWrapper.between("create_time", DateUtil.format(startTime.get(0), DateUtil.DATE_FORMAT_SECOND), DateUtil.format(startTime.get(1), DateUtil.DATE_FORMAT_SECOND));
        }
        queryWrapper.lambda().orderByDesc(SysLog::getCreateTime);
        return sysLogMapper.selectPage(new Page<>(sysLog.getPageNum(), sysLog.getPageSize()), queryWrapper);
    }
}
