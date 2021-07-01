package com.hope.blog.blog.service;

import com.hope.blog.blog.model.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
public interface BlogArticleService extends IService<BlogArticle> {

    List<BlogArticle> findListByPage(BlogArticle entity);
}
