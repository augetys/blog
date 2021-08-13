package com.hope.blog.blog.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by lijin on  2021/8/6
 */
@Data
public class BlogTagListResponse {
    @ApiModelProperty(value = "tag名称")
    private String name;

    @ApiModelProperty(value = "tagId")
    private String id;

    @ApiModelProperty(value = "tagId")
    private String icon;

    @ApiModelProperty(value = "文章数量")
    private Integer num;
}
