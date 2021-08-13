package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogTagSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogTagListResponse;
import com.hope.blog.blog.model.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客标签表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogTagService extends IService<BlogTag> {
    Page<BlogTag> findListByPage(BlogTagSearchRequestDto blogTagSearchRequestDto);

    List<BlogTag> findAll(BlogTagSearchRequestDto blogTagSearchRequestDto);

    List<BlogTagListResponse> getTag();

}
