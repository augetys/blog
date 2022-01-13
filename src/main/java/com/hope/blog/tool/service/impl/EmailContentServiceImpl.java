package com.hope.blog.tool.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.tool.dto.request.EmailSendRequestDto;
import com.hope.blog.tool.model.EmailContent;
import com.hope.blog.tool.mapper.EmailContentMapper;
import com.hope.blog.tool.service.EmailContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.utils.MailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Resource
    private EmailContentMapper emailContentMapper;

    @Override
    public boolean send(EmailSendRequestDto emailSendRequestDto) {
        List<String> tos = emailSendRequestDto.getTos();
        tos.forEach(
                item -> {
                    EmailContent emailContent = new EmailContent();
                    emailContent.setFromUser(CommonConstant.SENDER);
                    emailContent.setContent(emailSendRequestDto.getContent());
                    emailContent.setSubject(emailSendRequestDto.getSubject());
                    emailContent.setToUser(item);
                    emailContent.setCreateTime(new Date());
                    emailContentMapper.insert(emailContent);
                    MailUtil.sendWithHtml(emailSendRequestDto.getSubject(), emailSendRequestDto.getContent(), item);
                }
        );
        return true;
    }

    @Override
    public IPage<EmailContent> findListByPage(EmailContent entity) {
        return null;
    }
}
