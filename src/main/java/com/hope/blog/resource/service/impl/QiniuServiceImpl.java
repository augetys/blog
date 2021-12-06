package com.hope.blog.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
import com.hope.blog.resource.mapper.QiniuConfigMapper;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.resource.model.QiniuConfig;
import com.hope.blog.utils.FileUtil;
import com.hope.blog.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


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
public class QiniuServiceImpl extends ServiceImpl<QiniuContentMapper, QiniuContent> implements QiniuService {

    @Autowired
    private QiniuContentMapper qiniuContentMapper;

    @Autowired
    private QiniuConfigMapper qiniuConfigMapper;

    @Override
    public QiniuContent uploadFile(MultipartFile file, String bucket, String name) {
        try {
            String key = QiniuUtil.uploadFile(file, bucket);
            QiniuContent content = qiniuContentMapper.findByKey(key);
            QiniuConfig qiniuConfig = findConfigByBucket(bucket);
            if (content == null) {
                //存入数据库
                QiniuContent qiniuContent = new QiniuContent();
                qiniuContent.setSuffix(FileUtil.getExtensionName(key));
                qiniuContent.setName(name);
                qiniuContent.setBucket(bucket);
                qiniuContent.setType(qiniuConfig.getType());
                qiniuContent.setFileKey(FileUtil.getFileNameNoEx(key));
                qiniuContent.setUrl(qiniuConfig.getHost() + "/" + key);
                qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize() + "")));
                qiniuContentMapper.insert(qiniuContent);
                return qiniuContent;
            } else {
                Asserts.fail("七牛云文件已存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("七牛云文件上传失败！");
        }
        return null;
    }

    @Override
    public List<QiniuContent> uploadFiles(HttpServletRequest request, String bucket) {
        List<QiniuContent> contents = new ArrayList<>();
        List<MultipartFile> multipartFileList = FileUtil.getMultipartFileList(request);
        multipartFileList.forEach(
                item -> {
                    contents.add(uploadFile(item, bucket, null));
                }
        );
        return contents;
    }

    @Override
    public List<QiniuConfig> findConfig() {
        QueryWrapper<QiniuConfig> queryWrapper = new QueryWrapper<>();
        return qiniuConfigMapper.selectList(queryWrapper);
    }

    @Override
    public QiniuConfig findConfigByBucket(String bucket) {
        QueryWrapper<QiniuConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bucket", bucket);
        return qiniuConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateConfig(QiniuConfig entity) {
        return qiniuConfigMapper.updateById(entity) > 0;
    }

    @Override
    public IPage<QiniuContent> findListByPage(FileSearchRequestDto fileSearchRequestDto) {
        QueryWrapper<QiniuContent> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = fileSearchRequestDto.getRealName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("real_name", name);
        }
        queryWrapper.lambda().orderByAsc(QiniuContent::getUpdateTime);
        Page<QiniuContent> page = new Page<>();
        page.setCurrent(fileSearchRequestDto.getPageNum());
        page.setSize(fileSearchRequestDto.getPageSize());
        return qiniuContentMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean deleteById(String id) {
        QiniuContent qiniuContent = qiniuContentMapper.selectById(id);
        return QiniuUtil.deleteFile(qiniuContent.getBucket(), qiniuContent.getFileKey()) && qiniuContentMapper.deleteById(id) > 0;
    }
}
