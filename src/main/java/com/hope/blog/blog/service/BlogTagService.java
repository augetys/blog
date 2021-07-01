package com.hope.blog.blog.service;

import com.hope.blog.blog.model.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客标签表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
public interface BlogTagService extends IService<BlogTag> {

    List<BlogTag> findListByPage(BlogTag entity);
}
