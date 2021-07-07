package com.hope.blog.resource.service;

import com.hope.blog.resource.model.QiniuContent;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 七牛云文件存储 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
public interface QiniuService {
    QiniuContent uploadPhoto(MultipartFile file);
}
