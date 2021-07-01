package com.hope.blog.blog.service.impl;

import com.hope.blog.blog.model.BlogTag;
import com.hope.blog.blog.mapper.BlogTagMapper;
import com.hope.blog.blog.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
@Service
@Transactional
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Override
    public List<BlogTag> findListByPage(BlogTag entity) {
        return null;
    }
}
