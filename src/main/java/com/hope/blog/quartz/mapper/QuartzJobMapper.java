package com.hope.blog.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hope.blog.quartz.model.QuartzJob;
import java.util.List;


/**
 * @author lijin
 * @since 2021-07-02
 */
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {
    /**
     * 查询启用的任务
     * @return List
     */
    List<QuartzJob> findByIsPauseIsFalse();
}
