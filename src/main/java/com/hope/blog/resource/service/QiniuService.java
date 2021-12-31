package com.hope.blog.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;

import com.hope.blog.resource.model.QiniuContent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 七牛云文件存储 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
public interface QiniuService extends IService<QiniuContent> {
    QiniuContent uploadFile(MultipartFile file, String bucket, String name);

    List<QiniuContent> uploadFiles(HttpServletRequest request, String bucket);

    IPage<QiniuContent> findListByPage(FileSearchRequestDto fileSearchRequestDto);

    boolean deleteById(String id);

    boolean updateById(QiniuContent qiniuContent);

    boolean synchronize();

    List<String> findBucket();
}
