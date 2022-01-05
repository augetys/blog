package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by lijin on  2021/7/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDictDetailStatusRequetDto {

    @NotBlank(message = "字典详情Id不能为空")
    @ApiModelProperty(value = "字典详情Id")
    private String id;

    @NotNull(message = "启用状态不能为空")
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;
}
