package com.hope.blog.utils;

import com.google.gson.Gson;
import com.hope.blog.common.constant.Secret;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.File;


/**
 * Created by lijin on  2021/6/24
 */
@Slf4j
public class QiniuUtil {
    /**
     * 七牛云上传图片
     *
     * @param localFilePath
     * @return
     */
    public static String uploadPhoto(File localFilePath) throws QiniuException {
        //配置类，配置为华南，若不指定，则会使用 自动判断 区域，使用相应域名处理。
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Response response = uploadManager.put(localFilePath, key, getUploadToken());
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        log.info("{七牛图片上传key: " + putRet.key + ",七牛图片上传hash: " + putRet.hash + "}");
        return putRet.key;
    }


    public static String getUploadToken() {
        Auth auth = Auth.create(Secret.ACCESSKEY, Secret.SECRETKEY);
        return auth.uploadToken(Secret.BUCKET_PHOTO);
    }
}
