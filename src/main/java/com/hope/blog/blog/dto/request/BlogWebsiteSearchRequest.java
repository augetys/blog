package com.hope.blog.blog.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogWebsiteSearchRequest extends PageInfo {
    @ApiModelProperty(value = "网址名称")
    private String name;
}
