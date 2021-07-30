package com.hope.blog.blog.service.impl;

import com.hope.blog.blog.model.BlogComment;
import com.hope.blog.blog.mapper.BlogCommentMapper;
import com.hope.blog.blog.service.BlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客文章评论表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Service
@Transactional
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {
    @Override
    public List<BlogComment> findListByPage(BlogComment entity) {
        return null;
    }
}
