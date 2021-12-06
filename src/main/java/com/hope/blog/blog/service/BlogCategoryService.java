package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogCategorySearchRequestDto;
import com.hope.blog.blog.model.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogCategoryService extends IService<BlogCategory> {
    IPage<BlogCategory> findListByPage(BlogCategorySearchRequestDto blogCategorySearchRequestDto);

    List<BlogCategory> findAll(BlogCategorySearchRequestDto blogCategorySearchRequestDto);

    BlogCategory findById(String id);
}
