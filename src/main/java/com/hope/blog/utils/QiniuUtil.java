package com.hope.blog.utils;

import com.alibaba.fastjson.JSON;
import com.hope.blog.resource.model.QiniuConfig;
import com.hope.blog.resource.service.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * Created by lijin on  2021/6/24
 */
@Slf4j
public class QiniuUtil {

    private static final String HUAD = "华东";

    private static final String HUAB = "华北";

    private static final String HUAN = "华南";

    private static final String BEIM = "北美";

    /**
     * 获取七牛云Auth对象
     *
     * @return
     */
    private static Auth authInstance() {
        QiniuService qiniuService = SpringContextHolder.getBean(QiniuService.class);
        QiniuConfig config = qiniuService.findConfig().get(0);
        // 密钥配置
        return Auth.create(config.getAccessKey(), config.getSecretKey());
    }

    /**
     * 指定bucket上传，获取token
     *
     * @return
     */
    private static String getUpToken(String bucketName) {
        return authInstance().uploadToken(bucketName);
    }

    /**
     * 上传文件到指定bucketName
     *
     * @param file       byte[]
     * @param bucketName
     */
    public static String uploadFile(MultipartFile file, String bucketName) {
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        // 创建上传对象
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Response res = uploadManager.put(file.getBytes(), key, getUpToken(bucketName));
            DefaultPutRet putRet = JSON.parseObject(res.bodyString(), DefaultPutRet.class);
            // 返回文件Key
            return putRet.key;
        } catch (QiniuException e) {
            Response r = e.response;
            log.error("上传七牛云异常:{}", r.toString());
        } catch (IOException e) {
            log.error("上传七牛云异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除文件
     * @param bucketName
     * @param keys
     * @return
     */
    public static boolean BatchDeleteImg(String bucketName, List<String> keys) {
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(authInstance(), cfg);
        BucketManager.BatchOperations operations = new BucketManager.BatchOperations();
        for (String key : keys) {
            operations.addDeleteOp(bucketName, key);
        }
        try {
            bucketManager.batch(operations);
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            log.error("七牛云文件删除错误:{}", r.toString());
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除一个文件
     *
     * @param key
     */
    public static boolean deleteFile(String bucketName, String key) {
        // 实例化一个 bucketManage
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(authInstance(), cfg);
        try {
            bucketManager.delete(bucketName, key);
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            log.error("七牛云文件删除错误:{}", r.toString());
            return false;
        }
    }

    /**
     * 得到机房的对应关系
     *
     * @param zone 机房名称
     * @return Region
     */
    public static Region getRegion(String zone) {

        if (HUAD.equals(zone)) {
            return Region.huadong();
        } else if (HUAB.equals(zone)) {
            return Region.huabei();
        } else if (HUAN.equals(zone)) {
            return Region.huanan();
        } else if (BEIM.equals(zone)) {
            return Region.beimei();
            // 否则就是东南亚
        } else {
            return Region.qvmHuadong();
        }
    }
}
