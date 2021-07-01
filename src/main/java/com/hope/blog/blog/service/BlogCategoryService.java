package com.hope.blog.blog.service;

import com.hope.blog.blog.model.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    List<BlogCategory> findListByPage(BlogCategory entity);
}
