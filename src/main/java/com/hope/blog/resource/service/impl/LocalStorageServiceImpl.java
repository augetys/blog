package com.hope.blog.resource.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.resource.config.FileProperties;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.mapper.LocalStorageMapper;
import com.hope.blog.resource.service.LocalStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.FileUtil;
import com.hope.blog.utils.LocalStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
    public IPage<LocalStorage> findListByPage(FileSearchRequestDto fileSearchRequestDto) {
        QueryWrapper<LocalStorage> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = fileSearchRequestDto.getRealName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("real_name", name);
        }
        queryWrapper.lambda().orderByAsc(LocalStorage::getCreateTime);

        Page<LocalStorage> page = new Page<>();
        page.setCurrent(fileSearchRequestDto.getPageNum());
        page.setSize(fileSearchRequestDto.getPageSize());
        return localStorageMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean deleteById(String id) {
        LocalStorage localStorage = localStorageMapper.selectById(id);
        String path = localStorage.getPath();
        return LocalStorageUtil.deleteFile(path) && localStorageMapper.deleteById(id) > 0;
    }

    @Override
    public LocalStorage uploadFile(MultipartFile multipartFile, String name) {
        // 检查文件大小
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        // 上传文件
        File file = LocalStorageUtil.upload(multipartFile, properties.getFile() + File.separator);
        if (ObjectUtil.isNull(file)) {
            Asserts.fail("上传失败");
        }
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        LocalStorage localStorage = new LocalStorage();
        localStorage.setName(StringUtils.isEmpty(name) ? file.getName() : name);
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
    public List<LocalStorage> uploadFiles(HttpServletRequest request) {
        List<LocalStorage> localStorages = new ArrayList<>();
        List<MultipartFile> multipartFileList = FileUtil.getMultipartFileList(request);
        multipartFileList.forEach(
                item -> {
                    localStorages.add(uploadFile(item,null));
                }
        );
        return localStorages;
    }

}
