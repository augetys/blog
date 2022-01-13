package com.hope.blog.utils;

import com.alibaba.fastjson.JSON;
import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.common.exception.BusinessException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * Created by lijin on  2021/6/24
 */
@Slf4j
public class QiniuUtil {

    public static final String AK = "6riz6eaJfEtERl28wRX1pQfHgcn6X-WS69N1xgLx";

    public static final String SK = "LXJ0AfRwisSgLfvqDqLcENjgaTK_76CIEHeFrPT_";

    public static final String PHOTO = "https://photo.choot.top/";

    public static final String FILE = "http://file.choot.top/";

    public static final String VIDEO = "http://video.choot.top/";


    /**
     * 获取url
     *
     * @return
     */
    public static String getUrl(String bucketName, String key) {
        if (bucketName.equals(CommonConstant.PHOTO)) {
            return PHOTO + key;
        }
        if (bucketName.equals(CommonConstant.FILE)) {
            return FILE + key;
        }
        if (bucketName.equals(CommonConstant.VIDEO)) {
            return VIDEO + key;
        }
        return "";
    }


    /**
     * 获取七牛云Auth对象
     *
     * @return
     */
    private static Auth authInstance() {
        return Auth.create(AK, SK);
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
    public static FileInfo uploadFile(MultipartFile file, String bucketName) {
        try {
            // 检查文件大小
            if (FileUtil.checkSize(100L, file.getSize())) {
                throw new BusinessException("文件超出大小！");
            }
            String key = UUID.randomUUID().toString().replaceAll("-", "");
            // 创建上传对象
            Configuration cfg = new Configuration();
            UploadManager uploadManager = new UploadManager(cfg);
            Response res = uploadManager.put(file.getBytes(), key, getUpToken(bucketName));
            DefaultPutRet putRet = JSON.parseObject(res.bodyString(), DefaultPutRet.class);
            log.info("key:{}", putRet.key);
            log.info("hash:{}", putRet.hash);
            // 返回文件Key
            return getFileInfo(bucketName, putRet.key);
        } catch (Exception e) {
            throw new BusinessException("七牛云上传失败！", e);
        }
    }

    /**
     * 批量删除文件
     *
     * @param bucketName
     * @param keys
     * @return
     */
    public static boolean BatchDeleteImg(String bucketName, List<String> keys) {
        try {
            Configuration cfg = new Configuration();
            BucketManager bucketManager = new BucketManager(authInstance(), cfg);
            BucketManager.BatchOperations operations = new BucketManager.BatchOperations();
            for (String key : keys) {
                operations.addDeleteOp(bucketName, key);
            }
            bucketManager.batch(operations);
            return true;
        } catch (QiniuException e) {
            throw new BusinessException("七牛云批量文件删除错误！", e);
        }
    }


    /**
     * 删除一个文件
     *
     * @param key
     */
    public static boolean deleteFile(String bucketName, String key) {
        try {
            // 实例化一个 bucketManage
            Configuration cfg = new Configuration();
            BucketManager bucketManager = new BucketManager(authInstance(), cfg);
            bucketManager.delete(bucketName, key);
            return true;
        } catch (QiniuException e) {
            throw new BusinessException("七牛云文件删除错误！", e);
        }
    }

    public static FileInfo getFileInfo(String bucketName, String fileKey) {
        // 实例化一个 bucketManage
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(authInstance(), cfg);
        try {
            FileInfo stat = bucketManager.stat(bucketName, fileKey);
            stat.key = fileKey;
            return stat;
        } catch (QiniuException e) {
            throw new BusinessException("获取" + fileKey + "文件信息失败！", e);
        }
    }

    /**
     * 获取所有的bucket
     */
    public static List<String> getBucketsInfo() {
        try {
            // 实例化一个 bucketManage
            Configuration cfg = new Configuration();
            BucketManager bucketManager = new BucketManager(authInstance(), cfg);
            //获取所有的bucket信息
            String[] bucketNms = bucketManager.buckets();
            return Arrays.asList(bucketNms);
        } catch (Exception e) {
            throw new BusinessException("获取bucket失败！", e);
        }
    }

    /**
     * 获取bucket下的所有文件
     *
     * @param bucketName
     * @return
     */
    public static List<FileInfo> findAll(String bucketName) {
        List<FileInfo> fileInfos = new ArrayList<>();
        // 实例化一个 bucketManage
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(authInstance(), cfg);
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            fileInfos.addAll(Arrays.asList(fileListIterator.next()));
        }
        return fileInfos;
    }
}
