package com.hope.blog.sys.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.common.exception.Asserts;
import com.hope.blog.sys.dto.request.EmailSendRequestDto;
import com.hope.blog.sys.model.ConfigEmail;
import com.hope.blog.sys.mapper.ConfigEmailMapper;
import com.hope.blog.sys.service.ConfigEmailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 邮箱配置 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Service
@Transactional
public class ConfigEmailServiceImpl extends ServiceImpl<ConfigEmailMapper, ConfigEmail> implements ConfigEmailService {

    @Autowired
    private ConfigEmailMapper configEmailMapper;

    @Override
    public ConfigEmail find() {
        QueryWrapper<ConfigEmail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "1");
        return configEmailMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean send(EmailSendRequestDto emailSendRequestDto, ConfigEmail emailConfig) {
        if (emailConfig == null) {
            Asserts.fail("请先设置邮箱配置");
        }
        // 封装
        MailAccount account = new MailAccount();
        String user = emailConfig.getFromUser().split("@")[0];
        account.setUser(user);
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        account.setPass(emailConfig.getPass());
        // ssl方式发送
        account.setSslEnable(true);
        // 使用STARTTLS安全连接
        account.setStartttlsEnable(true);
        String content = emailSendRequestDto.getContent();
        // 发送
        try {
            int size = emailSendRequestDto.getTos().size();
            Mail.create(account)
                    .setTos(emailSendRequestDto.getTos().toArray(new String[size]))
                    .setTitle(emailSendRequestDto.getSubject())
                    .setContent(content)
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("邮件发送失败！");
        }
        return false;
    }
}
