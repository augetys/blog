package com.hope.blog.auth.dto.response;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hope.blog.auth.model.SysMenus;
import com.hope.blog.common.base.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by lijin on  2021/6/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenusResponseDto extends BaseVO<SysMenus> {
    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "描述")
    private String name;

    @ApiModelProperty(value = "菜单url")
    private String path;

    @ApiModelProperty(value = "前端图标")
    private String icon;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单级别")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
