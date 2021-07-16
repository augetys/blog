package com.hope.blog.tool.service;

import com.hope.blog.tool.dto.request.EmailSendRequestDto;
import com.hope.blog.tool.model.EmailConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮箱配置 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface EmailConfigService extends IService<EmailConfig> {
    EmailConfig find();

    boolean send(EmailSendRequestDto emailSendRequestDto, EmailConfig emailConfig);
}
