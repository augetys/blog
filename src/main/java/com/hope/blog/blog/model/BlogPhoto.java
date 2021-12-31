package com.hope.blog.blog.model;

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

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 相册
 * </p>
 *
 * @author lijin
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_photo")
@ApiModel(value = "BlogPhoto对象", description = "相册")
public class BlogPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "图片地址不能为空")
    @ApiModelProperty(value = "图片地址")
    private String url;

    @NotBlank(message = "描述不能为空")
    @ApiModelProperty(value = "描述")
    private String tip;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;


}
