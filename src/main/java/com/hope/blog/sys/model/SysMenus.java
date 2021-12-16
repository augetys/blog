package com.hope.blog.sys.model;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 后台用户菜单表
 * </p>
 *
 * @author lijin
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menus")
@ApiModel(value = "SysMenus对象", description = "后台用户菜单表")
public class SysMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @Length(min = 1, max = 50, message = "菜单名称在 1 到 50 个字符")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @NotBlank(message = "菜单url不为空")
    @ApiModelProperty(value = "菜单url")
    private String path;

    @NotBlank(message = "前端图标不为空")
    @ApiModelProperty(value = "前端图标")
    private String icon;

    @DecimalMax(value = "9999")
    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单级别")
    private Integer level;

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
