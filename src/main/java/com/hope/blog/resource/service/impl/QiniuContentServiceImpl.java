package com.hope.blog.resource.service.impl;

import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.service.QiniuContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 七牛云文件存储 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Service
@Transactional
public class QiniuContentServiceImpl extends ServiceImpl<QiniuContentMapper, QiniuContent> implements QiniuContentService {

}
