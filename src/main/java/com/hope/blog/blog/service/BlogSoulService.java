package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogSoulSearchRequest;
import com.hope.blog.blog.model.BlogSoul;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心灵鸡汤表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
public interface BlogSoulService extends IService<BlogSoul> {
    IPage<BlogSoul> findListByPage(BlogSoulSearchRequest blogSoulSearchRequest);

    List<BlogSoul> random();
}
