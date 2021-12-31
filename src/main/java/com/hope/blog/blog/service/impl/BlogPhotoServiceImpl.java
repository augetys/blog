package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogPhotoSearchRequest;
import com.hope.blog.blog.model.BlogPhoto;
import com.hope.blog.blog.mapper.BlogPhotoMapper;
import com.hope.blog.blog.service.BlogPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.utils.QiniuUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 相册 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
@Service
@Transactional
public class BlogPhotoServiceImpl extends ServiceImpl<BlogPhotoMapper, BlogPhoto> implements BlogPhotoService {

    @Resource
    private BlogPhotoMapper blogPhotoMapper;

    @Resource
    private QiniuContentMapper qiniuContentMapper;

    @Override
    public IPage<BlogPhoto> findListByPage(BlogPhotoSearchRequest blogPhotoSearchRequest) {
        QueryWrapper<BlogPhoto> queryWrapper = new QueryWrapper<>();
        //构建条件
        String tip = blogPhotoSearchRequest.getTip();
        if (!StringUtils.isEmpty(tip)) {
            queryWrapper.like("tip", tip);
        }
        queryWrapper.orderByDesc("create_time");
        return blogPhotoMapper.selectPage(new Page<>(blogPhotoSearchRequest.getPageNum(), blogPhotoSearchRequest.getPageSize()), queryWrapper);
    }

    @Override
    public List<BlogPhoto> findAll(BlogPhotoSearchRequest blogPhotoSearchRequest) {
        return this.list();
    }

    @Override
    public boolean delete(String id) {
        BlogPhoto blogPhoto = blogPhotoMapper.selectById(id);
        String url = blogPhoto.getUrl();
        QueryWrapper<QiniuContent> queryWrapper = new QueryWrapper<>();
        //构建条件
        if (!StringUtils.isEmpty(url)) {
            queryWrapper.eq("url", url);
        }
        QiniuContent qiniuContent = qiniuContentMapper.selectOne(queryWrapper);
        return QiniuUtil.deleteFile(qiniuContent.getBucket(), qiniuContent.getFileKey()) && qiniuContentMapper.deleteById(qiniuContent.getId()) > 0 && blogPhotoMapper.deleteById(id) > 0;
    }
}
