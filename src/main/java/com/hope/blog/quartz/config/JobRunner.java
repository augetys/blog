package com.hope.blog.quartz.config;

import com.hope.blog.quartz.mapper.QuartzJobMapper;
import com.hope.blog.quartz.model.QuartzJob;
import com.hope.blog.quartz.utils.QuartzManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lijin
 * @since 2021-07-02
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JobRunner implements ApplicationRunner {

    private final QuartzJobMapper quartzJobMapper;
    private final QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobMapper.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzManage::addJob);
        log.info("--------------------定时任务注入完成---------------------");
    }
}
