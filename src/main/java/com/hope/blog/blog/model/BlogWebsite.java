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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 网址导航表
 * </p>
 *
 * @author lijin
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_website")
@ApiModel(value = "BlogWebsite对象", description = "网址导航表")
public class BlogWebsite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "导航名称不能为空")
    @ApiModelProperty(value = "导航名称")
    private String name;

    @NotBlank(message = "跳转地址不能为空")
    @ApiModelProperty(value = "跳转地址")
    private String url;

    @Range(min=1,max=4,message="类别是1-4的整数")
    @ApiModelProperty(value = "类别（1:常用网址 2:学习 3:资源 4:影视）")
    private Integer category;

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
