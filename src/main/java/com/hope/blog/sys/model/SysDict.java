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
@ApiModel(value="SysDict对象", description="数据字典")
public class SysDict implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
