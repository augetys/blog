package com.hope.blog.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.log.dto.request.SysExceptionLogSearchRequestDto;
import com.hope.blog.log.model.SysExceptionLog;
import com.hope.blog.log.mapper.SysExceptionLogMapper;
import com.hope.blog.log.service.SysExceptionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 异常日志表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-06
 */
@Service
@Transactional
public class SysExceptionLogServiceImpl extends ServiceImpl<SysExceptionLogMapper, SysExceptionLog> implements SysExceptionLogService {

    @Autowired
    private SysExceptionLogMapper sysExceptionLogMapper;

    @Override
    public Page<SysExceptionLog> findListByPage(SysExceptionLogSearchRequestDto sysExceptionLogSearchRequestDto) {
        QueryWrapper<SysExceptionLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sysExceptionLogSearchRequestDto.getOperation())) {
            queryWrapper.like("operation", sysExceptionLogSearchRequestDto.getOperation());
        }
        if (!StringUtils.isEmpty(sysExceptionLogSearchRequestDto.getUserName())) {
            queryWrapper.eq("user_name", sysExceptionLogSearchRequestDto.getUserName());
        }
        if (!StringUtils.isEmpty(sysExceptionLogSearchRequestDto.getCreateTime())) {
            List<Date> startTime = sysExceptionLogSearchRequestDto.getCreateTime();
            queryWrapper.between("create_time", DateUtil.format(startTime.get(0), DateUtil.DATE_FORMAT_SECOND), DateUtil.format(startTime.get(1), DateUtil.DATE_FORMAT_SECOND));
        }
        queryWrapper.lambda().orderByAsc(SysExceptionLog::getCreateTime);
        return sysExceptionLogMapper.selectPage(new Page<>(sysExceptionLogSearchRequestDto.getPageNum(), sysExceptionLogSearchRequestDto.getPageSize()), queryWrapper);
    }
}
