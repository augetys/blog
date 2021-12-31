package com.hope.blog.blog.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * Create by lijin on 2021/7/4 10:15
 */
@Data
@Document(indexName = "blog", replicas = 0)
public class BlogArticleListResponseDto {

    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    @ApiModelProperty(value = "博客标题")
    @Field(type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String title;

    @ApiModelProperty(value = "博客简介")
    @Field(type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String summary;

    @ApiModelProperty(value = "博客内容")
    @Field(type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
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
    private String articlePart;

    @ApiModelProperty(value = "文章作者")
    private String articleAuthor;

    @ApiModelProperty(value = "博客分类ID")
    private String categoryId;

    @ApiModelProperty(value = "博客分类名称")
    private String categoryName;

    @ApiModelProperty(value = "博客分类图标")
    private String categoryIcon;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "是否开启评论(0:否 1:是)")
    private Integer openComment;

    @ApiModelProperty(value = "上传人id")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
