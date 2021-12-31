package com.hope.blog.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.comment.dto.request.BlogCommentQueryRequest;
import com.hope.blog.comment.dto.response.BlogCommentResponse;
import com.hope.blog.comment.model.BlogComment;
import com.hope.blog.comment.mapper.BlogCommentMapper;
import com.hope.blog.comment.model.BlogCommentReplay;
import com.hope.blog.comment.service.BlogCommentReplayService;
import com.hope.blog.comment.service.BlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.utils.CopyUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * <p>
 * 博客文章评论表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Service
@Transactional
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Resource
    private BlogCommentReplayService blogCommentReplayService;

    @Override
    public IPage<BlogComment> findListByPage(BlogComment entity) {
        return null;
    }

    @Override
    public IPage<BlogCommentResponse> commentsByArticleId(BlogCommentQueryRequest blogCommentQueryRequest) {
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogCommentQueryRequest.getId())) {
            queryWrapper.eq("article_id", blogCommentQueryRequest.getId());
        }
        queryWrapper.orderByDesc("create_time");
        Page<BlogComment> page = new Page<>();
        page.setCurrent(blogCommentQueryRequest.getPageNum());
        page.setSize(blogCommentQueryRequest.getPageSize());
        IPage<BlogComment> pageList = this.page(page, queryWrapper);
        IPage<BlogCommentResponse> convert = pageList.convert(BlogComment -> CopyUtil.copy(BlogComment, BlogCommentResponse.class));
        List<BlogCommentResponse> records = convert.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            records.forEach(
                    item -> {
                        QueryWrapper<BlogCommentReplay> replayQueryWrapper = new QueryWrapper<>();
                        replayQueryWrapper.orderByAsc("create_time");
                        replayQueryWrapper.eq("comment_id", item.getId());
                        item.setCommentReplayList(blogCommentReplayService.list(replayQueryWrapper));
                    }
            );
        }
        return convert;
    }

    @Override
    public boolean commitComment(BlogComment entity) {
        if (StringUtils.isEmpty(entity.getUserId())) {
            entity.setUserId(UUID.randomUUID().toString().replace("-", ""));
        }
        if (StringUtils.isEmpty(entity.getUserAvatar())) {
            entity.setUserAvatar(CommonConstant.USERAVATAR);
        }
        entity.setCreateTime(new Date());
        return blogCommentMapper.insert(entity) > 0;
    }
}
