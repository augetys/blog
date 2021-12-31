package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogTagSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogTagListResponse;
import com.hope.blog.blog.model.BlogTag;
import com.hope.blog.blog.mapper.BlogTagMapper;
import com.hope.blog.blog.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Service
@Transactional
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Override
    public IPage<BlogTag> findListByPage(BlogTagSearchRequestDto blogTagSearchRequestDto) {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        // 构建条件
        String name = blogTagSearchRequestDto.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.lambda().orderByAsc(BlogTag::getSort);
        return blogTagMapper.selectPage(new Page<>(blogTagSearchRequestDto.getPageNum(), blogTagSearchRequestDto.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogTag> findAll(BlogTagSearchRequestDto blogTagSearchRequestDto) {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        // 构建条件
        String name = blogTagSearchRequestDto.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        return blogTagMapper.selectList(queryWrapper);
    }

    @Override
    public List<BlogTagListResponse> getTag() {
        return blogTagMapper.getTag();
    }
}
