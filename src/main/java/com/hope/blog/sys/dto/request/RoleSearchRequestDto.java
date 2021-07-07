package com.hope.blog.sys.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/6/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "角色名")
    private String name;
}
