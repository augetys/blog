package com.hope.blog.comment.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/11/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogCommentQueryRequest  extends PageInfo {
    @ApiModelProperty(value = "文章id")
    private String id;
}
