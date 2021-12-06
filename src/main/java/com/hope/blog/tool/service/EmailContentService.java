package com.hope.blog.tool.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.tool.model.EmailContent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮箱发送内容 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-16
 */
public interface EmailContentService extends IService<EmailContent> {
    IPage<EmailContent> findListByPage(EmailContent entity);
}
