package com.hope.blog.comment.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 博客文章评论回复表（子评论表）
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_comment_replay")
@ApiModel(value = "BlogCommentReplay对象", description = "博客文章评论回复表（子评论表）")
public class BlogCommentReplay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "评论主表id不能为空")
    @ApiModelProperty(value = "评论主表id")
    private String commentId;

    @ApiModelProperty(value = "评论者id")
    private String fromId;

    @NotBlank(message = "评论者名字不能为空")
    @ApiModelProperty(value = "评论者名字")
    private String fromName;

    @ApiModelProperty(value = "评论者头像")
    private String fromAvatar;

    @Email
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String mail;

    @NotBlank(message = "被评论者id不能为空")
    @ApiModelProperty(value = "被评论者id")
    private String toId;

    @NotBlank(message = "被评论者名字不能为空")
    @ApiModelProperty(value = "被评论者名字")
    private String toName;

    @ApiModelProperty(value = "被评论者头像")
    private String toAvatar;

    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
