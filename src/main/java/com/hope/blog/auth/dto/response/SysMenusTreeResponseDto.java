package com.hope.blog.auth.dto.response;

import com.hope.blog.auth.model.SysMenus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by lijin on  2021/6/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenusTreeResponseDto extends SysMenus {
    @ApiModelProperty(value = "子级菜单")
    private List<SysMenusTreeResponseDto> children;
}
