package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

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
    @ApiModelProperty(value = "字典名称列表")
    private List<String> namesList;
}
