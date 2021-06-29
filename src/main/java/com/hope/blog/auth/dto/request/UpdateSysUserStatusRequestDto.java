package com.hope.blog.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/6/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSysUserStatusRequestDto {
    @ApiModelProperty(value = "用户Id")
    private String id;
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;
}
