package com.hope.blog.blog.service.impl;

import com.hope.blog.blog.model.BlogReplay;
import com.hope.blog.blog.mapper.BlogReplayMapper;
import com.hope.blog.blog.service.BlogReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客文章回复表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
@Service
@Transactional
public class BlogReplayServiceImpl extends ServiceImpl<BlogReplayMapper, BlogReplay> implements BlogReplayService {

    @Override
    public List<BlogReplay> findListByPage(BlogReplay entity) {
        return null;
    }
}
