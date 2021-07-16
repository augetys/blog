package com.hope.blog.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.resource.mapper.QiniuConfigMapper;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.resource.model.QiniuConfig;
import com.hope.blog.utils.FileUtil;
import com.hope.blog.utils.QiniuUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class QiniuServiceImpl implements QiniuService {

    @Autowired
    private QiniuContentMapper qiniuContentMapper;

    @Autowired
    private QiniuConfigMapper qiniuConfigMapper;

    @Override
    public QiniuContent uploadPhoto(MultipartFile file) {
        QiniuConfig qiniuConfig = this.findConfig();
        if (qiniuConfig == null) {
            Asserts.fail("请先设置七牛云配置！");
        }
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiniuUtil.getRegion(qiniuConfig.getZone()));
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        String upToken = auth.uploadToken(qiniuConfig.getBucket());
        try {
            String key = QiniuUtil.createFileName(file.getOriginalFilename());
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            QiniuContent content = qiniuContentMapper.findByKey(FileUtil.getFileNameNoEx(putRet.key));
            if (content == null) {
                //存入数据库
                QiniuContent qiniuContent = new QiniuContent();
                qiniuContent.setSuffix(FileUtil.getExtensionName(putRet.key));
                qiniuContent.setBucket(qiniuConfig.getBucket());
                qiniuContent.setType(qiniuConfig.getType());
                qiniuContent.setFileKey(FileUtil.getFileNameNoEx(putRet.key));
                qiniuContent.setUrl(qiniuConfig.getHost() + "/" + putRet.key);
                qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize() + "")));
                qiniuContentMapper.insert(qiniuContent);
                return qiniuContent;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("文件上传失败！");
        }
        return null;
    }

    @Override
    public List<QiniuContent> uploadPhotos(HttpServletRequest request) {
        List<QiniuContent> contents = new ArrayList<>();
        List<MultipartFile> multipartFileList = FileUtil.getMultipartFileList(request);
        multipartFileList.forEach(
                item -> {
                    contents.add(uploadPhoto(item));
                }
        );
        return contents;
    }

    @Override
    public QiniuConfig findConfig() {
        QueryWrapper<QiniuConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id", 1);
        return qiniuConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateConfig(QiniuConfig entity) {
        return qiniuConfigMapper.updateById(entity) > 0;
    }
}
