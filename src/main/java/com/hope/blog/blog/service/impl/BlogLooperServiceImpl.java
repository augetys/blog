package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogLooperSearchRequestDto;
import com.hope.blog.blog.model.BlogLooper;
import com.hope.blog.blog.mapper.BlogLooperMapper;
import com.hope.blog.blog.service.BlogLooperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Autowired
    private BlogLooperMapper blogLooperMapper;

    @Override
    public Page<BlogLooper> findListByPage(BlogLooperSearchRequestDto blogLooperSearchRequestDto) {
        QueryWrapper<BlogLooper> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogLooperSearchRequestDto.getTitle())) {
            queryWrapper.like("title", blogLooperSearchRequestDto.getTitle());
        }
        return blogLooperMapper.selectPage(new Page<>(blogLooperSearchRequestDto.getPageNum(), blogLooperSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogLooper> findAll() {
        return this.list();
    }
}
