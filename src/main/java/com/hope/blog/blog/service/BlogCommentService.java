package com.hope.blog.blog.service;

import com.hope.blog.blog.model.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客文章评论表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
public interface BlogCommentService extends IService<BlogComment> {
    List<BlogComment> findListByPage(BlogComment entity);
}
