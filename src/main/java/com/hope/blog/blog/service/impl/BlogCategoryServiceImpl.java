package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogCategorySearchRequestDto;
import com.hope.blog.blog.model.BlogCategory;
import com.hope.blog.blog.mapper.BlogCategoryMapper;
import com.hope.blog.blog.service.BlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Service
@Transactional
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public Page<BlogCategory> findListByPage(BlogCategorySearchRequestDto blogCategorySearchRequestDto) {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = blogCategorySearchRequestDto.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.lambda().orderByAsc(BlogCategory::getSort);
        return blogCategoryMapper.selectPage(new Page<>(blogCategorySearchRequestDto.getPageNum(), blogCategorySearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogCategory> findAll(BlogCategorySearchRequestDto blogCategorySearchRequestDto) {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = blogCategorySearchRequestDto.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        return blogCategoryMapper.selectList(queryWrapper);
    }

    @Override
    public BlogCategory findById(String id) {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        //构建条件
        if (!StringUtils.isEmpty(id)) {
            queryWrapper.eq("id", id);
        }
        return blogCategoryMapper.selectOne(queryWrapper);
    }
}
