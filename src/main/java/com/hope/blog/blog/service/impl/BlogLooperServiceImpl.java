package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.model.BlogLooper;
import com.hope.blog.blog.mapper.BlogLooperMapper;
import com.hope.blog.blog.service.BlogLooperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
@Service
@Transactional
public class BlogLooperServiceImpl extends ServiceImpl<BlogLooperMapper, BlogLooper> implements BlogLooperService {
    @Override
    public Page<BlogLooper> findListByPage(BlogLooper entity) {
        return null;
    }
}
