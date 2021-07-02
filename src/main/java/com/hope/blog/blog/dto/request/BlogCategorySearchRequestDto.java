package com.hope.blog.blog.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/7/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogCategorySearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "标签名")
    private String name;
}
