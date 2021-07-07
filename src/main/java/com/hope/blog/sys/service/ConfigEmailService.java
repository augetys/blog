package com.hope.blog.sys.service;

import com.hope.blog.sys.dto.request.EmailSendRequestDto;
import com.hope.blog.sys.model.ConfigEmail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮箱配置 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
public interface ConfigEmailService extends IService<ConfigEmail> {
    ConfigEmail find();

    boolean send(EmailSendRequestDto emailSendRequestDto,ConfigEmail configEmail);
}
