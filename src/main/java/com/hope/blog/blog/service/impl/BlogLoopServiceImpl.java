package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogLoopSearchRequestDto;
import com.hope.blog.blog.model.BlogLoop;
import com.hope.blog.blog.mapper.BlogLoopMapper;
import com.hope.blog.blog.service.BlogLoopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
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
public class BlogLoopServiceImpl extends ServiceImpl<BlogLoopMapper, BlogLoop> implements BlogLoopService {

    @Resource
    private BlogLoopMapper blogLoopMapper;

    @Override
    public IPage<BlogLoop> findListByPage(BlogLoopSearchRequestDto blogLoopSearchRequestDto) {
        QueryWrapper<BlogLoop> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogLoopSearchRequestDto.getTitle())) {
            queryWrapper.like("title", blogLoopSearchRequestDto.getTitle());
        }
        queryWrapper.orderByAsc("sort");
        return blogLoopMapper.selectPage(new Page<>(blogLoopSearchRequestDto.getPageNum(), blogLoopSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogLoop> findAll() {
        QueryWrapper<BlogLoop> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        return blogLoopMapper.selectList(queryWrapper);
    }
}
