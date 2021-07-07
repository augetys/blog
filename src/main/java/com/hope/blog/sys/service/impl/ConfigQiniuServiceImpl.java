package com.hope.blog.sys.service.impl;

import com.hope.blog.sys.model.ConfigQiniu;
import com.hope.blog.sys.mapper.ConfigQiniuMapper;
import com.hope.blog.sys.service.ConfigQiniuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 七牛云配置 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Service
@Transactional
public class ConfigQiniuServiceImpl extends ServiceImpl<ConfigQiniuMapper, ConfigQiniu> implements ConfigQiniuService {

}
