package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogWebsiteSearchRequest;
import com.hope.blog.blog.dto.response.BlogWebsiteListResponse;
import com.hope.blog.blog.dto.response.BlogWebsiteResponse;
import com.hope.blog.blog.model.BlogWebsite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网址导航表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-12-29
 */
public interface BlogWebsiteService extends IService<BlogWebsite> {
    IPage<BlogWebsiteResponse> findListByPage(BlogWebsiteSearchRequest BlogWebsiteSearchRequest);

    List<BlogWebsiteListResponse> getNavigation();

}
