package com.hope.blog.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.blog.service.BlogCategoryService;
import com.hope.blog.sys.mapper.SysUserMapper;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.CopyUtil;
import com.hope.blog.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @Override
    public IPage<BlogArticleListResponseDto> findListByPage(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTitle())) {
            queryWrapper.like("title", blogArticleSearchRequestDto.getTitle());
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getCategoryId())) {
            queryWrapper.eq("category_id", blogArticleSearchRequestDto.getCategoryId());
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getIsOriginal())) {
            queryWrapper.eq("is_original", blogArticleSearchRequestDto.getIsOriginal());
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getIsPublish())) {
            queryWrapper.eq("is_publish", blogArticleSearchRequestDto.getIsPublish());
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getCreateTime())) {
            queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = '" + DateUtil.format(blogArticleSearchRequestDto.getCreateTime(), DateUtil.DATE_FORMAT_DAY) + "'");
        }

        Page<BlogArticle> page = new Page<>();
        page.setCurrent(blogArticleSearchRequestDto.getPageNum());
        page.setSize(blogArticleSearchRequestDto.getPageSize());
        IPage<BlogArticle> pageList = this.page(page, queryWrapper);

        // convert方法中是函数式接口,BearUtil不能用
        IPage<BlogArticleListResponseDto> convert = pageList.convert(BlogArticle -> CopyUtil.copy(BlogArticle, BlogArticleListResponseDto.class));
        List<BlogArticleListResponseDto> list = convert.getRecords();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(
                    item -> {
                        item.setAuthor(sysUserService.getById(item.getAdminId()).getUsername());
                        item.setCategoryName(blogCategoryService.getById(item.getCategoryId()).getName());
                    }
            );
            if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTagId())) {
                List<BlogArticleListResponseDto> collect = list.stream().filter(item -> Arrays.asList(item.getTagId().split(",")).contains(blogArticleSearchRequestDto.getTagId())).collect(Collectors.toList());
                convert.setRecords(collect);
            }
        }
        return convert;
    }

    @Override
    public List<BlogArticle> getHotArticle() {
        return blogArticleMapper.getHotArticle();
    }
}
