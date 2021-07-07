package com.hope.blog.sys.service;

import com.hope.blog.sys.model.ConfigQiniu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 七牛云配置 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface ConfigQiniuService extends IService<ConfigQiniu> {
    ConfigQiniu find();
}
