package com.hope.blog.blog.dto.response;

import com.hope.blog.blog.model.BlogWebsite;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create by lijin on 2022/2/13 15:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogWebsiteResponse extends BlogWebsite {

    @ApiModelProperty(value = "网址分类名称")
    private String categoryName;

}
