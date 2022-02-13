package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogWebsiteSearchRequest;

import com.hope.blog.blog.dto.response.BlogWebsiteListResponse;
import com.hope.blog.blog.dto.response.BlogWebsiteResponse;
import com.hope.blog.blog.model.BlogWebsite;
import com.hope.blog.blog.mapper.BlogWebsiteMapper;
import com.hope.blog.blog.service.BlogWebsiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.enums.Website;
import com.hope.blog.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 网址导航表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-12-29
 */
@Service
@Transactional
public class BlogWebsiteServiceImpl extends ServiceImpl<BlogWebsiteMapper, BlogWebsite> implements BlogWebsiteService {

    @Resource
    private BlogWebsiteMapper BlogWebsiteMapper;

    @Override
    public IPage<BlogWebsiteResponse> findListByPage(BlogWebsiteSearchRequest BlogWebsiteSearchRequest) {
        QueryWrapper<BlogWebsite> queryWrapper = new QueryWrapper<>();
        // 构建条件
        if (!StringUtils.isEmpty(BlogWebsiteSearchRequest.getName())) {
            queryWrapper.like("name", BlogWebsiteSearchRequest.getName());
        }
        if (!StringUtils.isEmpty(BlogWebsiteSearchRequest.getCategory())) {
            queryWrapper.eq("category", BlogWebsiteSearchRequest.getCategory());
        }
        queryWrapper.orderByDesc("create_time");
        IPage<BlogWebsiteResponse> pages = new Page<>();

        List<BlogWebsiteResponse> blogWebsiteResponsesList = CopyUtil.copyList(BlogWebsiteMapper.selectList(queryWrapper), BlogWebsiteResponse.class);
        if (!CollectionUtils.isEmpty(blogWebsiteResponsesList)) {
            blogWebsiteResponsesList.forEach(
                    item -> {
                        item.setCategoryName(Website.getName(item.getCategory()));
                    }
            );
            pages = CommonPage.getPages(BlogWebsiteSearchRequest.getPageNum(), BlogWebsiteSearchRequest.getPageSize(), blogWebsiteResponsesList);
        }
        return pages;
    }

    @Override
    public BlogWebsiteListResponse getNavigation() {
        BlogWebsiteListResponse BlogWebsiteListResponse = new BlogWebsiteListResponse();
        BlogWebsiteListResponse.setCommonList(BlogWebsiteMapper.commonList());
        BlogWebsiteListResponse.setSourceList(BlogWebsiteMapper.sourceList());
        BlogWebsiteListResponse.setStudyList(BlogWebsiteMapper.studyList());
        BlogWebsiteListResponse.setVideoList(BlogWebsiteMapper.videoList());
        return BlogWebsiteListResponse;
    }
}
