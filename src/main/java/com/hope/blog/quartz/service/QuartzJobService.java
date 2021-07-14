package com.hope.blog.quartz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.quartz.dto.request.JobUpdateStatusRequestDto;
import com.hope.blog.quartz.model.QuartzJob;
import com.hope.blog.quartz.model.QuartzLog;
import com.hope.blog.quartz.dto.request.JobQueryRequestDto;
import java.util.List;
import java.util.Set;

/**
 * @author lijin
 * @since 2021-07-02
 */
public interface QuartzJobService {

    /**
     * 分页查询
     * @param criteria 条件
     * @return /
     */
    Page<QuartzJob> findJobByPage(JobQueryRequestDto criteria);

    /**
     * 查询全部
     * @param criteria 条件
     * @return /
     */
    List<QuartzJob> queryAll(JobQueryRequestDto criteria);

    /**
     * 分页查询日志
     * @param criteria 条件
     * @return /
     */
    Page<QuartzLog> findLogByPage(JobQueryRequestDto criteria);

    /**
     * 查询全部
     * @param criteria 条件
     * @return /
     */
    List<QuartzLog> queryAllLog(JobQueryRequestDto criteria);

    /**
     * 创建
     * @param resources /
     */
    void create(QuartzJob resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(QuartzJob resources);

    /**
     * 删除任务
     * @param ids /
     */
    void delete(Set<String> ids);

    /**
     * 根据ID查询
     * @param id ID
     * @return /
     */
    QuartzJob findById(String id);

    /**
     * 更改定时任务状态
     * @param  jobUpdateStatusRequestDto/
     */
    void updateIsPause(JobUpdateStatusRequestDto jobUpdateStatusRequestDto);

    /**
     * 立即执行定时任务
     * @param quartzJob /
     */
    void execution(QuartzJob quartzJob);

    /**
     * 执行子任务
     * @param tasks /
     * @throws InterruptedException /
     */
    void executionSubJob(String[] tasks) throws InterruptedException;
}
