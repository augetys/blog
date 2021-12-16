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
 * 七牛云文件存储
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
@TableName("qiniu_content")
@ApiModel(value = "QiniuContent对象", description = "七牛云文件存储")
public class QiniuContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "Bucket 识别符不能为空")
    @ApiModelProperty(value = "Bucket 识别符")
    private String bucket;

    @NotBlank(message = "文件名称不能为空")
    @ApiModelProperty(value = "文件名称")
    private String name;

    @NotBlank(message = "文件key值不能为空")
    @ApiModelProperty(value = "文件key值")
    private String fileKey;

    @NotBlank(message = "文件大小不能为空")
    @ApiModelProperty(value = "文件大小")
    private String size;

    @NotBlank(message = "文件url不能为空")
    @ApiModelProperty(value = "文件url")
    private String url;

    @NotBlank(message = "文件类型不能为空")
    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件后缀")
    private String suffix;

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
