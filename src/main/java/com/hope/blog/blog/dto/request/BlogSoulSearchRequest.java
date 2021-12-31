package com.hope.blog.blog.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/12/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSoulSearchRequest extends PageInfo {
    @ApiModelProperty(value = "词句内容")
    private String content;
}
