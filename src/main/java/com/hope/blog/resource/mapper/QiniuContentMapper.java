package com.hope.blog.resource.mapper;

import com.hope.blog.resource.model.QiniuContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 七牛云文件存储 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
public interface QiniuContentMapper extends BaseMapper<QiniuContent> {

    QiniuContent findByKey(String fileNameNoEx);

}
