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

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict")
@ApiModel(value = "SysDict对象", description = "数据字典")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Length(min = 1, max = 8, message = "昵称长度在 1 到 8 个字符")
    @ApiModelProperty(value = "字典名称")
    private String name;

    @Length(min = 1, max = 50, message = "昵称长度在 1 到 50 个字符")
    @ApiModelProperty(value = "描述")
    private String description;

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
