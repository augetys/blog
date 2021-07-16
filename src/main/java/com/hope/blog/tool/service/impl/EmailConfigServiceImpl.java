package com.hope.blog.tool.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.common.exception.Asserts;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.tool.model.EmailConfig;
import com.hope.blog.tool.mapper.EmailConfigMapper;
import com.hope.blog.tool.service.EmailConfigService;
import com.hope.blog.tool.dto.request.EmailSendRequestDto;
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
public class EmailConfigServiceImpl extends ServiceImpl<EmailConfigMapper, EmailConfig> implements EmailConfigService {

    @Autowired
    private EmailConfigMapper emailConfigMapper;

    @Override
    public EmailConfig find() {
        QueryWrapper<EmailConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "1");
        return emailConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean send(EmailSendRequestDto emailSendRequestDto, EmailConfig emailConfig) {
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
