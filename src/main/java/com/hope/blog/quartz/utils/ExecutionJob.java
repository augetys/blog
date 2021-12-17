package com.hope.blog.quartz.utils;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import com.hope.blog.pool.ThreadPoolExecutorUtil;
import com.hope.blog.quartz.dto.request.JobUpdateStatusRequestDto;
import com.hope.blog.quartz.mapper.QuartzLogMapper;
import com.hope.blog.quartz.model.QuartzJob;
import com.hope.blog.quartz.model.QuartzLog;
import com.hope.blog.quartz.service.QuartzJobService;
import com.hope.blog.tool.dto.request.EmailSendRequestDto;
import com.hope.blog.tool.service.EmailContentService;
import com.hope.blog.utils.DateUtil;
import com.hope.blog.utils.SpringContextHolder;
import com.hope.blog.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 参考人人开源，https://gitee.com/renrenio/renren-security
 *
 * @author /
 * @date 2019-01-07
 */
@Async
@Slf4j
public class ExecutionJob extends QuartzJobBean {

    /**
     * 该处仅供参考
     */
    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();

    @Override
    public void executeInternal(JobExecutionContext context) {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        QuartzJob quartzJob = (QuartzJob) mergedJobDataMap.get(QuartzJob.JOB_KEY);
        String userId = (String) mergedJobDataMap.get("userId");
        // 获取spring bean
        QuartzLogMapper quartzLogMapper = SpringContextHolder.getBean(QuartzLogMapper.class);
        QuartzJobService quartzJobService = SpringContextHolder.getBean(QuartzJobService.class);
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setJobName(quartzJob.getJobName());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setMethodName(quartzJob.getMethodName());
        quartzLog.setParams(quartzJob.getParams());
        quartzLog.setCreateTime(new Date());
        quartzLog.setCreateBy(userId);
        long startTime = System.currentTimeMillis();

        quartzLog.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            log.info("--------------------------------------------------------------");
            log.info("任务开始执行，任务名称：" + quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = EXECUTOR.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            log.info("任务执行完毕，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            log.info("--------------------------------------------------------------");
            // 判断是否存在子任务
            if (!StringUtils.isEmpty(quartzJob.getSubTask())) {
                String[] tasks = quartzJob.getSubTask().split("[,，]");
                // 执行子任务
                quartzJobService.executionSubJob(tasks);
                // 子任务执行完成，修改任务状态
                quartzLog.setIsSuccess(1);
            }
            quartzLog.setIsSuccess(1);
        } catch (Exception e) {
            log.info("任务执行失败，任务名称：" + quartzJob.getJobName());
            log.info("--------------------------------------------------------------");
            long times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            // 任务状态 1：成功 0：失败
            quartzLog.setIsSuccess(0);
            quartzLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
            if (quartzJob.getPauseAfterFailure() == 1) {
                //更新状态
                quartzJobService.updateIsPause(JobUpdateStatusRequestDto.builder().id(quartzJob.getId()).isPause(1).build());
            }
            if (quartzJob.getEmail() != null) {
                EmailContentService emailService = SpringContextHolder.getBean(EmailContentService.class);
                // 邮箱报警
                if (!StringUtils.isEmpty(quartzJob.getEmail())) {
                    EmailSendRequestDto emailVo = taskAlarm(quartzJob, ThrowableUtil.getStackTrace(e));
                    emailService.send(emailVo);
                }
            }
        } finally {
            quartzLogMapper.insert(quartzLog);
        }
    }

    private EmailSendRequestDto taskAlarm(QuartzJob quartzJob, String msg) {
        EmailSendRequestDto emailVo = new EmailSendRequestDto();
        emailVo.setSubject("定时任务【" + quartzJob.getJobName() + "】执行失败，请尽快处理！");
        Map<String, Object> data = new HashMap<>(16);
        data.put("task", quartzJob);
        data.put("msg", msg);
        data.put("year", String.valueOf(DateUtil.getYears()));
        // 传参到邮件模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("templates/email/taskAlarm.vm");
        emailVo.setContent(template.render(data));
        List<String> emails = Arrays.asList(quartzJob.getEmail().split("[,，]"));
        emailVo.setTos(emails);
        return emailVo;
    }
}
