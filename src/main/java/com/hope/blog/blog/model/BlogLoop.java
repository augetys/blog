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
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author lijin
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_loop")
@ApiModel(value = "BlogLoop对象", description = "轮播图")
public class BlogLoop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "轮播图标题")
    private String title;

    @DecimalMax(value = "9999")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @NotNull(message = "是否外链不能为空")
    @ApiModelProperty(value = "是否外链 0 否 1 是")
    private Integer isLink;

    @NotBlank(message = "目标URL不能为空")
    @ApiModelProperty(value = "目标URL")
    private String targetUrl;

    @NotBlank(message = "图片地址不能为空")
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}
