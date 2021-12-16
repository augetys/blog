package com.hope.blog.tool.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 邮箱发送内容
 * </p>
 *
 * @author lijin
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("email_content")
@ApiModel(value = "EmailContent对象", description = "邮箱发送内容")
public class EmailContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "发件人不能为空")
    @ApiModelProperty(value = "发件人")
    private String fromUser;

    @ApiModelProperty(value = "邮件主题")
    private String subject;

    @ApiModelProperty(value = "邮件内容")
    private String content;

    @NotBlank(message = "收件人不能为空")
    @ApiModelProperty(value = "收件人")
    private String toUser;

    @ApiModelProperty(value = "发送时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
}
