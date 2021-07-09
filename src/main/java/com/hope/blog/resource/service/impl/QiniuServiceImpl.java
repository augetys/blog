package com.hope.blog.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.resource.mapper.QiniuContentMapper;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.sys.model.ConfigQiniu;
import com.hope.blog.sys.service.ConfigQiniuService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


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
    private ConfigQiniuService configQiniuService;

    @Autowired
    private QiniuContentMapper qiniuContentMapper;

    @Override
    public QiniuContent uploadPhoto(MultipartFile file) {
        ConfigQiniu configQiniu = configQiniuService.find();
        if (configQiniu == null) {
            Asserts.fail("请先设置七牛云配置！");
        }
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiniuUtil.getRegion(configQiniu.getZone()));
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(configQiniu.getAccessKey(), configQiniu.getSecretKey());
        String upToken = auth.uploadToken(configQiniu.getBucket());
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
                qiniuContent.setBucket(configQiniu.getBucket());
                qiniuContent.setType(configQiniu.getType());
                qiniuContent.setFileKey(FileUtil.getFileNameNoEx(putRet.key));
                qiniuContent.setUrl(configQiniu.getHost() + "/" + putRet.key);
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
}
