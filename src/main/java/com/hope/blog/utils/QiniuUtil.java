package com.hope.blog.utils;

import com.qiniu.storage.Region;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;


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

    /**
     * 生成七牛云文件名
     * 规则为日期加上文件hash值
     *
     * @return
     */
    public static String createFileName(String file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        Date date = new Date();
        return sdf.format(date) + "." + FileUtil.getExtensionName(file);
    }
}
