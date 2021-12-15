package com.hope.blog.utils;

import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Created by lijin on  2021/12/14
 * MailProperties 如果是properties配置文件可以用此类读取
 */
@Slf4j
public class MailUtil {

    /**
     * 发送简单文本的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    public static boolean send(String subject, String content, String... to) {
        log.info("## Ready to send mail ...");
        JavaMailSenderImpl javaMailSender = SpringContextHolder.getBean(JavaMailSenderImpl.class);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件发送来源
        simpleMailMessage.setFrom(CommonConstant.SENDER);
        // 邮件发送目标
        simpleMailMessage.setTo(to);
        // 设置标题
        simpleMailMessage.setSubject(subject);
        // 设置内容
        simpleMailMessage.setText(content);

        try {
            // 发送
            javaMailSender.send(simpleMailMessage);
            log.info("## Send the mail success ...");
        } catch (Exception e) {
            throw new BusinessException("Send mail error: ", e);
        }

        return true;
    }

    /**
     * 发送 html 的邮件
     *
     * @param to
     * @param subject
     * @param html
     * @return
     */
    public static boolean sendWithHtml(String subject, String html, String... to) {
        log.info("## Ready to send mail ...");
        JavaMailSenderImpl javaMailSender = SpringContextHolder.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(CommonConstant.SENDER);
            // 邮件发送目标
            mimeMessageHelper.setTo(to);
            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);
            log.info("## Send the mail with html success ...");
        } catch (Exception e) {
            throw new BusinessException("Send html mail error:", e);
        }

        return true;
    }

    /**
     * 发送带有图片的 html 的邮件
     *
     * @param to
     * @param subject
     * @param html
     * @param cids
     * @param filePaths
     * @return
     */
    public static boolean sendWithImageHtml(String subject, String html, String[] cids, String[] filePaths, String... to) {
        log.info("## Ready to send mail ...");
        JavaMailSenderImpl javaMailSender = SpringContextHolder.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(CommonConstant.SENDER);
            // 邮件发送目标
            mimeMessageHelper.setTo(to);
            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(html, true);

            // 设置 html 中内联的图片
            for (int i = 0; i < cids.length; i++) {
                FileSystemResource file = new FileSystemResource(filePaths[i]);
                // addInline() 方法 cid 需要 html 中的 cid (Content ID) 对应，才能设置图片成功，
                // 具体可以参见，下面 4.3.3 单元测试的参数设置
                mimeMessageHelper.addInline(cids[i], file);
            }

            javaMailSender.send(mimeMessage);
            log.info("## Send the mail with image success ...");
        } catch (Exception e) {
            throw new BusinessException("Send html mail error:", e);
        }

        return true;
    }

    /**
     * 发送带有附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @return
     */
    public static boolean sendWithWithEnclosure(String subject, String content, String[] filePaths, String... to) {
        log.info("## Ready to send mail ...");
        JavaMailSenderImpl javaMailSender = SpringContextHolder.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(CommonConstant.SENDER);
            // 邮件发送目标
            mimeMessageHelper.setTo(to);
            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容
            mimeMessageHelper.setText(content);

            // 添加附件
            for (int i = 0; i < filePaths.length; i++) {
                FileSystemResource file = new FileSystemResource(filePaths[i]);
                String attachementFileName = "附件" + (i + 1);
                mimeMessageHelper.addAttachment(attachementFileName, file);
            }

            javaMailSender.send(mimeMessage);
            log.info("## Send the mail with enclosure success ...");
        } catch (Exception e) {
            throw new BusinessException("Send html mail error:", e);
        }
        return true;
    }
}
