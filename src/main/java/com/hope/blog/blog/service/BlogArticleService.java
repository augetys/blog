package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.model.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogArticleService extends IService<BlogArticle> {
    Page<BlogArticle> findListByPage(BlogArticleSearchRequestDto blogArticleSearchRequestDto);

    List<BlogArticle> getHotArticle();

    Page<BlogArticle> findByTag(BlogArticleSearchRequestDto blogArticleSearchRequestDto);

    Page<BlogArticle> findByCategory(BlogArticleSearchRequestDto blogArticleSearchRequestDto);
}
