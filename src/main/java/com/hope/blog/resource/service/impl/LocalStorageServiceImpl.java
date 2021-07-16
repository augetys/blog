package com.hope.blog.resource.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.resource.config.FileProperties;
import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.mapper.LocalStorageMapper;
import com.hope.blog.resource.service.LocalStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.FileUtil;
import com.hope.blog.utils.LocalStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 本地存储 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Service
@Transactional
public class LocalStorageServiceImpl extends ServiceImpl<LocalStorageMapper, LocalStorage> implements LocalStorageService {

    @Autowired
    private LocalStorageMapper localStorageMapper;
    @Autowired
    private FileProperties properties;

    @Override
    public LocalStorage uploadPhoto(MultipartFile multipartFile) {
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        File file = LocalStorageUtil.upload(multipartFile, properties.getPhoto() + File.separator);
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        if (ObjectUtil.isNull(file)) {
            Asserts.fail("上传失败");
        }
        LocalStorage localStorage = new LocalStorage();
        localStorage.setName(file.getName());
        localStorage.setRealName(multipartFile.getOriginalFilename());
        localStorage.setPath(file.getPath());
        localStorage.setSuffix(suffix);
        localStorage.setType(type);
        localStorage.setSize(FileUtil.getSize(multipartFile.getSize()));
        if (localStorageMapper.insert(localStorage) < 0) {
            // 删除文件
            LocalStorageUtil.deleteFile(file.getName());
        }
        return localStorage;
    }

    @Override
    public List<LocalStorage> uploadPhotos(HttpServletRequest request) {
        List<LocalStorage> localStorages = new ArrayList<>();
        List<MultipartFile> multipartFileList = FileUtil.getMultipartFileList(request);
        multipartFileList.forEach(
                item -> {
                    localStorages.add(uploadPhoto(item));
                }
        );
        return localStorages;
    }
}
