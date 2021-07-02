package com.hope.blog.blog.service;

import com.hope.blog.blog.model.BlogReplay;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 博客文章回复表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogReplayService extends IService<BlogReplay> {
    List<BlogReplay> findListByPage(BlogReplay entity);
}
