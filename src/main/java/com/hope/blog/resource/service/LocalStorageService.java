package com.hope.blog.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
import com.hope.blog.resource.model.LocalStorage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * <p>
 * 本地存储 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
public interface LocalStorageService extends IService<LocalStorage> {

    LocalStorage uploadFile(MultipartFile file, String name);

    List<LocalStorage> uploadFiles(HttpServletRequest request);

    IPage<LocalStorage> findListByPage(FileSearchRequestDto fileSearchRequestDto);

    boolean deleteById(String id);
}
