package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogWebsiteSearchRequest;
import com.hope.blog.blog.dto.response.BlogWebsiteResponse;
import com.hope.blog.blog.model.BlogWebsite;
import com.hope.blog.blog.mapper.BlogWebsiteMapper;
import com.hope.blog.blog.service.BlogWebsiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


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
    public IPage<BlogWebsite> findListByPage(BlogWebsiteSearchRequest BlogWebsiteSearchRequest) {
        QueryWrapper<BlogWebsite> queryWrapper = new QueryWrapper<>();
        // 构建条件
        String name = BlogWebsiteSearchRequest.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("create_time");
        return BlogWebsiteMapper.selectPage(new Page<>(BlogWebsiteSearchRequest.getPageNum(), BlogWebsiteSearchRequest.getPageSize()), queryWrapper);
    }

    @Override
    public BlogWebsiteResponse getNavigation() {
        BlogWebsiteResponse BlogWebsiteResponse = new BlogWebsiteResponse();
        BlogWebsiteResponse.setCommonList(BlogWebsiteMapper.commonList());
        BlogWebsiteResponse.setSourceList(BlogWebsiteMapper.sourceList());
        BlogWebsiteResponse.setStudyList(BlogWebsiteMapper.studyList());
        BlogWebsiteResponse.setVideoList(BlogWebsiteMapper.videoList());
        return BlogWebsiteResponse;
    }
}
