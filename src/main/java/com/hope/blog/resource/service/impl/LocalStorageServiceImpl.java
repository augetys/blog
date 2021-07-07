package com.hope.blog.resource.service.impl;

import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.mapper.LocalStorageMapper;
import com.hope.blog.resource.service.LocalStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 本地存储 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Service
@Transactional
public class LocalStorageServiceImpl extends ServiceImpl<LocalStorageMapper, LocalStorage> implements LocalStorageService {

}
