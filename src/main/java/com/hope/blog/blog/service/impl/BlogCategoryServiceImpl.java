package com.hope.blog.blog.service.impl;

import com.hope.blog.blog.model.BlogCategory;
import com.hope.blog.blog.mapper.BlogCategoryMapper;
import com.hope.blog.blog.service.BlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
@Service
@Transactional
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Override
    public List<BlogCategory> findListByPage(BlogCategory entity) {
        return null;
    }
}
