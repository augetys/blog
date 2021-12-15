package com.hope.blog.comment.service.impl;

import com.hope.blog.comment.model.BlogCommentReplay;
import com.hope.blog.comment.mapper.BlogCommentReplayMapper;
import com.hope.blog.comment.service.BlogCommentReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.common.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

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
public class BlogCommentReplayServiceImpl extends ServiceImpl<BlogCommentReplayMapper, BlogCommentReplay> implements BlogCommentReplayService {

    @Autowired
    private BlogCommentReplayMapper blogCommentReplayMapper;

    @Override
    public boolean commitReplay(BlogCommentReplay entity) {
        if (StringUtils.isEmpty(entity.getFromId())) {
            entity.setFromId(UUID.randomUUID().toString().replace("-", ""));
        }
        if (StringUtils.isEmpty(entity.getFromAvatar())) {
            entity.setFromAvatar(CommonConstant.USERAVATAR);
        }
        return blogCommentReplayMapper.insert(entity) > 0;
    }
}
