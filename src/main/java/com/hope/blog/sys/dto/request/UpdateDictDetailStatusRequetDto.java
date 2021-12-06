package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/7/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDictDetailStatusRequetDto {
    @ApiModelProperty(value = "字典详情Id")
    private String id;
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;
}
