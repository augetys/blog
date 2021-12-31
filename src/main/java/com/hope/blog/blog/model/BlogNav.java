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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 导航
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
@TableName("blog_nav")
@ApiModel(value = "BlogNav对象", description = "导航")
public class BlogNav implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Length(min = 1, max = 8, message = "用户名长度在 1 到 8 个字符")
    @NotBlank(message = "导航名称不能为空")
    @ApiModelProperty(value = "导航名称")
    private String name;

    @NotBlank(message = "导航图标不能为空")
    @ApiModelProperty(value = "导航图标")
    private String icon;

    @DecimalMax(value = "9999")
    @ApiModelProperty(value = "导航排序")
    private Integer sort;

    @NotBlank(message = "跳转路由不能为空")
    @ApiModelProperty(value = "跳转路由")
    private String path;

    @NotBlank(message = "是否打开新窗口不能为空")
    @ApiModelProperty(value = "是否打开新窗口")
    private String target;

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
