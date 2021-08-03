package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Service
@Transactional
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Override
    public Page<BlogArticle> findListByPage(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTitle())) {
            queryWrapper.like("title", blogArticleSearchRequestDto.getTitle());
        }
        return blogArticleMapper.selectPage(new Page<>(blogArticleSearchRequestDto.getPageNum(), blogArticleSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogArticle> getHotArticle() {
        return blogArticleMapper.getHotArticle();
    }

    @Override
    public Page<BlogArticle> findByTag(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        Page<BlogArticle> page = new Page<>();
        page.setCurrent(blogArticleSearchRequestDto.getPageNum());
        page.setSize(blogArticleSearchRequestDto.getPageSize());
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        IPage<BlogArticle> pageList = blogArticleMapper.selectPage(page, queryWrapper);
        List<BlogArticle> list = pageList.getRecords();
        return null;
    }

    @Override
    public Page<BlogArticle> findByCategory(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTitle())) {
            queryWrapper.like("article_category_id", blogArticleSearchRequestDto.getCategoryId());
        }
        return blogArticleMapper.selectPage(new Page<>(blogArticleSearchRequestDto.getPageNum(), blogArticleSearchRequestDto.getPageSize()), queryWrapper);
    }
}
