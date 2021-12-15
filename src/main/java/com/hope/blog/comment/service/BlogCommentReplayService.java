package com.hope.blog.comment.service;

import com.hope.blog.comment.model.BlogCommentReplay;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客文章评论回复表（子评论表） 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
public interface BlogCommentReplayService extends IService<BlogCommentReplay> {
    boolean commitReplay(BlogCommentReplay entity);
}
