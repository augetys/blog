package com.hope.blog.resource.model;

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

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 本地存储
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("local_storage")
@ApiModel(value = "LocalStorage对象", description = "本地存储")
public class LocalStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "文件真实的名称")
    private String realName;

    @NotBlank(message = "文件名不能为空")
    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "后缀")
    private String suffix;

    @NotBlank(message = "存储路径不能为空")
    @ApiModelProperty(value = "存储路径")
    private String path;

    @NotBlank(message = "访问路径不能为空")
    @ApiModelProperty(value = "访问路径")
    private String url;

    @ApiModelProperty(value = "类型")
    private String type;

    @NotBlank(message = "大小不能为空")
    @ApiModelProperty(value = "大小")
    private String size;

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
