package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogLoopSearchRequestDto;
import com.hope.blog.blog.model.BlogLoop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
public interface BlogLoopService extends IService<BlogLoop> {
    Page<BlogLoop> findListByPage(BlogLoopSearchRequestDto blogLoopSearchRequestDto);

    List<BlogLoop> findAll();
}
