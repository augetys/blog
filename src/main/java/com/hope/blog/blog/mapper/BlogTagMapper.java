package com.hope.blog.blog.mapper;

import com.hope.blog.blog.dto.response.BlogTagListResponse;
import com.hope.blog.blog.model.BlogTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 博客标签表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    List<BlogTagListResponse> getTag();
}
