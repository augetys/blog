package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.model.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.blog.common.api.CommonPage;

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
    IPage<BlogArticleListResponseDto> findListByPage(BlogArticleSearchRequestDto blogArticleSearchRequestDto);

    List<BlogArticle> getHotArticle();

    IPage<BlogArticleListResponseDto> keyword(BlogArticleSearchRequestDto blogArticleSearchRequestDto);

}
