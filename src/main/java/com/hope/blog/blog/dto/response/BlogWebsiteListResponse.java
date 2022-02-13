package com.hope.blog.blog.dto.response;

import com.hope.blog.blog.model.BlogWebsite;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by lijin on  2021/12/29
 */
@Data
public class BlogWebsiteListResponse {

    @ApiModelProperty(value = "常用网址导航")
    private List<BlogWebsite> commonList;

    @ApiModelProperty(value = "常用网址导航")
    private List<BlogWebsite> studyList;

    @ApiModelProperty(value = "常用网址导航")
    private List<BlogWebsite> sourceList;

    @ApiModelProperty(value = "常用网址导航")
    private List<BlogWebsite> videoList;

}
