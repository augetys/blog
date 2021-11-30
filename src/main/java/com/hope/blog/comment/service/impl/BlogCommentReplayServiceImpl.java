package com.hope.blog.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.comment.model.BlogCommentReplay;
import com.hope.blog.comment.mapper.BlogCommentReplayMapper;
import com.hope.blog.comment.service.BlogCommentReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 博客文章评论回复表（子评论表） 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Service
@Transactional
public class BlogCommentReplayServiceImpl extends ServiceImpl<BlogCommentReplayMapper, BlogCommentReplay>implements BlogCommentReplayService {
    @Override
    public IPage<BlogCommentReplay> findListByPage(BlogCommentReplay entity){
        return null;
    }
}
