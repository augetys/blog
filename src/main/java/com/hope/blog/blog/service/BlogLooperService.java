package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.model.BlogLooper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
public interface BlogLooperService extends IService<BlogLooper> {
    Page<BlogLooper> findListByPage(BlogLooper entity);
}
