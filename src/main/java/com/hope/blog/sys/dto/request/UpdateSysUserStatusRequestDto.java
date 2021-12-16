package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Created by lijin on  2021/6/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSysUserStatusRequestDto {

    @NotBlank(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id")
    private String id;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

}
