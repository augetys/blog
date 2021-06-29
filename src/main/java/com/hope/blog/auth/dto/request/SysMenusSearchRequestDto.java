package com.hope.blog.auth.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/6/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenusSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "父级菜单id")
    private String parentId;
    @ApiModelProperty(value = "菜单级数")
    private String level;
}
