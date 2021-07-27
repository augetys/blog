package com.hope.blog.blog.mapper;

import com.hope.blog.blog.model.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    List<BlogArticle> getHotArticle();

}
