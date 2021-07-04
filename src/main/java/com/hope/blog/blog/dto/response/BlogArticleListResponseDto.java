package com.hope.blog.blog.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Create by lijin on 2021/7/4 10:15
 */
@Data
public class BlogArticleListResponseDto {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "博客简介")
    private String summary;

    @ApiModelProperty(value = "上传人id")
    private String adminId;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "是否发布：0：否，1：是")
    private Integer isPublish;

    @ApiModelProperty(value = "标签id")
    private String tagId;

    @ApiModelProperty(value = "博客标签")
    private List<String> tagList;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "博客点击数")
    private Integer clickCount;

    @ApiModelProperty(value = "是否原创（0:不是 1：是）")
    private Integer isOriginal;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "文章出处")
    private String articlesPart;

    @ApiModelProperty(value = "博客分类ID")
    private String articleCategoryId;

    @ApiModelProperty(value = "博客分类名称")
    private String articleCategoryName;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "是否开启评论(0:否 1:是)")
    private Integer openComment;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
