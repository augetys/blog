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

    @ApiModelProperty(value = "导航名称")
    private String navName;

    @ApiModelProperty(value = "导航网站")
    private List<BlogWebsite> WebsiteList;

}
