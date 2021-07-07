package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Create by lijin on 2021/6/15 19:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSysMenusStatusRequestDto {
    @ApiModelProperty(value = "菜单Id")
    private String id;
    @ApiModelProperty(value = "是否显示：0->不显示；1->显示")
    private Integer isShow;
}
