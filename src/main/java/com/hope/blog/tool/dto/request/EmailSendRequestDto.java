package com.hope.blog.tool.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发送邮件时，接收参数的类
 *
 * @author 郑杰
 * @date 2018/09/28 12:02:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendRequestDto {

    @ApiModelProperty(value = "收件人，支持多个收件人")
    private List<String> tos;

    @ApiModelProperty(value = "主题")
    private String subject;

    @ApiModelProperty(value = "内容")
    private String content;
}
