package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Create by lijin on 2021/6/15 19:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSysMenusStatusRequestDto {

    @NotBlank(message = "菜单Id不能为空")
    @ApiModelProperty(value = "菜单Id")
    private String id;

    @NotNull(message = "是否显示不能为空")
    @ApiModelProperty(value = "是否显示：0->不显示；1->显示")
    private Integer isShow;
}
