package com.hope.blog.tool.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.tool.model.EmailContent;
import com.hope.blog.tool.mapper.EmailContentMapper;
import com.hope.blog.tool.service.EmailContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 邮箱发送内容 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-16
 */
@Service
@Transactional
public class EmailContentServiceImpl extends ServiceImpl<EmailContentMapper, EmailContent> implements EmailContentService {
    @Override
    public Page<EmailContent> findListByPage(EmailContent entity) {
        return null;
    }
}
