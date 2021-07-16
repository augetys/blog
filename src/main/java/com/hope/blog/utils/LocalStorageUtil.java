package com.hope.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lijin on  2021/7/15
 */
@Slf4j
public class LocalStorageUtil {
    /**
     * 将文件名解析成文件的上传路径
     */
    public static File upload(MultipartFile file, String filePath) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        String nowStr = format.format(date);
        try {
            String fileName = nowStr + "." + suffix;
            String path = filePath + fileName;
            // 创建目录
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    System.out.println("was not successful.");
                }
            }
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件：{} 成功！", fileName);
                return true;
            } else {
                log.error("删除单个文件：{} 失败！", fileName);
                return false;
            }
        } else {
            log.error("删除单个文件失败：{} 不存在！", fileName);
            return false;
        }
    }

    /**
     * 批量删除文件
     *
     * @param fileNameList
     * @return
     */
    public static boolean deleteFileList(List<String> fileNameList) {
        int successCount = 0;
        for (String fileName : fileNameList) {
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("删除单个文件：{} 成功！", fileName);
                    successCount += 1;
                } else {
                    log.error("删除单个文件：{} 失败！", fileName);
                }
            } else {
                log.error("删除单个文件失败：{} 不存在！", fileName);
            }
        }
        if (successCount == fileNameList.size()) {
            log.info("所有文件删除成功！");
            return true;
        } else {
            log.error("存在删除失败的文件！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = LocalStorageUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = LocalStorageUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            log.error("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            log.error("删除目录 {} 成功！", dir);
            return true;
        } else {
            return false;
        }
    }
}
