package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleNavSearchRequestDto;
import com.hope.blog.blog.model.BlogNav;
import com.hope.blog.blog.mapper.BlogNavMapper;
import com.hope.blog.blog.service.BlogNavService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 导航 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
@Service
@Transactional
public class BlogNavServiceImpl extends ServiceImpl<BlogNavMapper, BlogNav> implements BlogNavService {

    @Autowired
    private BlogNavMapper blogNavMapper;

    @Override
    public Page<BlogNav> findListByPage(BlogArticleNavSearchRequestDto blogArticleNavSearchRequestDto) {
        QueryWrapper<BlogNav> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogArticleNavSearchRequestDto.getName())) {
            queryWrapper.like("title", blogArticleNavSearchRequestDto.getName());
        }
        queryWrapper.lambda().orderByAsc(BlogNav::getSort);
        return blogNavMapper.selectPage(new Page<>(blogArticleNavSearchRequestDto.getPageNum(), blogArticleNavSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogNav> findAll() {
        QueryWrapper<BlogNav> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(BlogNav::getSort);
        return blogNavMapper.selectList(queryWrapper);
    }
}
