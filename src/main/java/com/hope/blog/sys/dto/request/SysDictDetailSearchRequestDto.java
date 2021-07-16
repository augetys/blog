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
public class SysDictDetailSearchRequestDto {
    @ApiModelProperty(value = "字典id")
    private String dictId;
    @ApiModelProperty(value = "标签名称")
    private String label;
}
