package com.hope.blog.sys.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/7/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDictSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "名称")
    private String name;
}
