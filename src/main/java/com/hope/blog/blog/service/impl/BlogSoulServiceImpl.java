package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogSoulSearchRequest;
import com.hope.blog.blog.model.BlogSoul;
import com.hope.blog.blog.mapper.BlogSoulMapper;
import com.hope.blog.blog.service.BlogSoulService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 心灵鸡汤表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
@Service
@Transactional
public class BlogSoulServiceImpl extends ServiceImpl<BlogSoulMapper, BlogSoul> implements BlogSoulService {

    @Resource
    private BlogSoulMapper blogSoulMapper;

    @Override
    public IPage<BlogSoul> findListByPage(BlogSoulSearchRequest blogSoulSearchRequest) {
        QueryWrapper<BlogSoul> queryWrapper = new QueryWrapper<>();
        //构建条件
        String content = blogSoulSearchRequest.getContent();
        if (!StringUtils.isEmpty(content)) {
            queryWrapper.like("content", content);
        }
        queryWrapper.orderByDesc("create_time");
        return blogSoulMapper.selectPage(new Page<>(blogSoulSearchRequest.getPageNum(), blogSoulSearchRequest.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogSoul> random() {
        return this.list();
    }
}
