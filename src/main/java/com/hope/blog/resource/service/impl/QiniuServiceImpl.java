package com.hope.blog.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.common.exception.BusinessException;
import com.hope.blog.resource.dto.request.FileSearchRequestDto;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.utils.FileUtil;
import com.hope.blog.utils.QiniuUtil;
import com.qiniu.storage.model.FileInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QiniuServiceImpl extends ServiceImpl<QiniuContentMapper, QiniuContent> implements QiniuService {

    @Autowired
    private QiniuContentMapper qiniuContentMapper;

    @Override
    public QiniuContent uploadFile(MultipartFile file, String bucket, String name) {
        FileInfo fileInfo = QiniuUtil.uploadFile(file, bucket);
        QiniuContent content = qiniuContentMapper.findByKey(fileInfo.key);
        if (content == null) {
            //存入数据库
            QiniuContent qiniuContent = new QiniuContent();
            qiniuContent.setSuffix(FileUtil.getExtensionName(fileInfo.key));
            qiniuContent.setName(name);
            qiniuContent.setBucket(bucket);
            qiniuContent.setType(fileInfo.mimeType);
            qiniuContent.setFileKey(fileInfo.key);
            qiniuContent.setUrl(QiniuUtil.getUrl(bucket,fileInfo.key));
            qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize() + "")));
            qiniuContentMapper.insert(qiniuContent);
            return qiniuContent;
        } else {
            throw new BusinessException("七牛云文件已存在");
        }
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
    public IPage<QiniuContent> findListByPage(FileSearchRequestDto fileSearchRequestDto) {
        QueryWrapper<QiniuContent> queryWrapper = new QueryWrapper<>();
        //构建条件
        String name = fileSearchRequestDto.getRealName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("real_name", name);
        }
        queryWrapper.lambda().orderByAsc(QiniuContent::getCreateTime);
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

    @Override
    public boolean updateById(QiniuContent entity) {
        return qiniuContentMapper.updateById(entity) > 0;
    }

    @Override
    public boolean synchronize() {
        // 获取所有的bucket
        List<String> buckets = this.findBucket();
        buckets.forEach(
                item -> {
                    List<FileInfo> fileInfos = QiniuUtil.findAll(item);
                    fileInfos.forEach(
                            item1 -> {
                                if (qiniuContentMapper.findByKey(item1.key) == null) {
                                    QiniuContent qiniuContent = new QiniuContent();
                                    qiniuContent.setSuffix(FileUtil.getExtensionName(item1.key));
                                    qiniuContent.setName(item1.key);
                                    qiniuContent.setBucket(item);
                                    qiniuContent.setType(item1.mimeType);
                                    qiniuContent.setFileKey(item1.key);
                                    qiniuContent.setUrl(QiniuUtil.getUrl(item,item1.key));
                                    qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(item1.fsize + "")));
                                    qiniuContentMapper.insert(qiniuContent);
                                }
                            }
                    );
                }
        );
        return true;
    }

    @Override
    public List<String> findBucket() {
        return QiniuUtil.getBucketsInfo();
    }
}
