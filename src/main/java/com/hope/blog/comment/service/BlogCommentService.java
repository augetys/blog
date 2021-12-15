package com.hope.blog.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.comment.dto.request.BlogCommentQueryRequest;
import com.hope.blog.comment.dto.response.BlogCommentResponse;
import com.hope.blog.comment.model.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客文章评论表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
public interface BlogCommentService extends IService<BlogComment> {
    IPage<BlogComment> findListByPage(BlogComment entity);

    IPage<BlogCommentResponse> commentsByArticleId(BlogCommentQueryRequest blogCommentQueryRequest);

    boolean commitComment(BlogComment entity);
}
