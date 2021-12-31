package com.hope.blog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.blog.dto.request.BlogPhotoSearchRequest;
import com.hope.blog.blog.model.BlogPhoto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 相册 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
public interface BlogPhotoService extends IService<BlogPhoto> {
    IPage<BlogPhoto> findListByPage(BlogPhotoSearchRequest blogPhotoSearchRequest);

    List<BlogPhoto> findAll(BlogPhotoSearchRequest blogPhotoSearchRequest);

    boolean delete(String id);
}
