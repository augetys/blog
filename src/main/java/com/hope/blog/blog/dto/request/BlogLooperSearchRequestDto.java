package com.hope.blog.blog.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Create by lijin on 2021/7/27 21:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogLooperSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "标题")
    private String title;
}
