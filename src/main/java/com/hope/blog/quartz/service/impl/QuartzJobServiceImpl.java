package com.hope.blog.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.quartz.dto.request.JobUpdateStatusRequestDto;
import com.hope.blog.quartz.mapper.QuartzJobMapper;
import com.hope.blog.quartz.mapper.QuartzLogMapper;
import com.hope.blog.quartz.model.QuartzJob;
import com.hope.blog.quartz.model.QuartzLog;
import com.hope.blog.quartz.service.QuartzJobService;
import com.hope.blog.quartz.dto.request.JobQueryRequestDto;
import com.hope.blog.quartz.utils.QuartzManage;
import com.hope.blog.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.quartz.CronExpression;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author lijin
 * @since 2021-07-02
 */
@RequiredArgsConstructor
@Service(value = "quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {

    private final QuartzJobMapper quartzJobMapper;
    private final QuartzLogMapper quartzLogMapper;
    private final QuartzManage quartzManage;
    private final RedisUtil redisUtil;


    @Override
    public IPage<QuartzJob> findJobByPage(JobQueryRequestDto jobQueryRequestDto) {
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(jobQueryRequestDto.getJobName())) {
            queryWrapper.like("job_name", jobQueryRequestDto.getJobName());
        }
        if (!StringUtils.isEmpty(jobQueryRequestDto.getIsPause())) {
            queryWrapper.eq("is_pause", jobQueryRequestDto.getIsPause());
        }
        return quartzJobMapper.selectPage(new Page<>(jobQueryRequestDto.getPageNum(), jobQueryRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<QuartzJob> queryAll(JobQueryRequestDto jobQueryRequestDto) {
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<>();
        return quartzJobMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<QuartzLog> findLogByPage(JobQueryRequestDto jobQueryRequestDto) {
        QueryWrapper<QuartzLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(jobQueryRequestDto.getJobName())) {
            queryWrapper.like("job_name", jobQueryRequestDto.getJobName());
        }
        if (!StringUtils.isEmpty(jobQueryRequestDto.getIsSuccess())) {
            queryWrapper.eq("is_success", jobQueryRequestDto.getIsSuccess());
        }
        return quartzLogMapper.selectPage(new Page<>(jobQueryRequestDto.getPageNum(), jobQueryRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<QuartzLog> queryAllLog(JobQueryRequestDto jobQueryRequestDto) {
        QueryWrapper<QuartzLog> queryWrapper = new QueryWrapper<>();
        return quartzLogMapper.selectList(queryWrapper);
    }

    @Override
    public QuartzJob findById(String id) {
        return quartzJobMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            Asserts.fail("cron表达式格式错误");
        }
        quartzJobMapper.insert(quartzJob);
        quartzManage.addJob(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            Asserts.fail("cron表达式格式错误");
        }
        if (!StringUtils.isEmpty(quartzJob.getSubTask())) {
            List<String> tasks = Arrays.asList(quartzJob.getSubTask().split("[,，]"));
            if (tasks.contains(quartzJob.getId())) {
                Asserts.fail("子任务中不能添加当前任务ID");
            }
        }
        quartzJobMapper.updateById(quartzJob);
        quartzManage.updateJobCron(quartzJob);
    }

    @Override
    public void updateIsPause(JobUpdateStatusRequestDto jobUpdateStatusRequestDto) {
        QuartzJob quartzJob = quartzJobMapper.selectById(jobUpdateStatusRequestDto.getId());
        if (jobUpdateStatusRequestDto.getIsPause() == 0) {
            quartzManage.resumeJob(quartzJob);
        }
        if (jobUpdateStatusRequestDto.getIsPause() == 1) {
            quartzManage.pauseJob(quartzJob);
        }
        quartzJob.setIsPause(jobUpdateStatusRequestDto.getIsPause());
        quartzJobMapper.updateById(quartzJob);
    }

    @Override
    public void execution(QuartzJob quartzJob) {
        quartzManage.runJobNow(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            QuartzJob quartzJob = findById(id);
            quartzManage.deleteJob(quartzJob);
            quartzJobMapper.deleteById(quartzJob.getId());
        }
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executionSubJob(String[] tasks) throws InterruptedException {
        for (String id : tasks) {
            QuartzJob quartzJob = findById(id);
            // 执行任务
            execution(quartzJob);
        }
    }
}
