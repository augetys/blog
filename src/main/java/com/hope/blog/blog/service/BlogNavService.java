package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogArticleNavSearchRequestDto;
import com.hope.blog.blog.model.BlogNav;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 导航 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
public interface BlogNavService extends IService<BlogNav> {
    IPage<BlogNav> findListByPage(BlogArticleNavSearchRequestDto blogArticleNavSearchRequestDto);

    List<BlogNav> findAll();
}
