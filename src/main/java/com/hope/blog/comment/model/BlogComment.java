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
 * 博客文章评论表
 * </p>
 *
 * @author lijin
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_comment")
@ApiModel(value = "BlogComment对象", description = "博客文章评论表")
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论用户")
    private String userId;

    @NotBlank(message = "用户昵称不能为空")
    @ApiModelProperty(value = "评论用户昵称")
    private String nickName;

    @ApiModelProperty(value = "评论用户头像")
    private String userAvatar;

    @Email
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "点赞的数量")
    private Integer likeNum;

    @ApiModelProperty(value = "博客文章id")
    private String articleId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
