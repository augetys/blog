package com.hope.blog.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
@ApiModel(value = "BlogArticle对象", description = "博客文章表")
@Document(indexName = "blog", replicas = 0)
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Id
    private String id;

    @NotBlank(message = "博客标题不能为空")
    @Field(type = FieldType.Text,searchAnalyzer = "ik_max_word",analyzer = "ik_max_word")
    @ApiModelProperty(value = "博客标题")
    private String title;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "博客简介")
    private String summary;

    @NotBlank(message = "博客内容不能为空")
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "是否发布：0：否，1：是")
    private Integer isPublish;

    @NotBlank(message = "标签id不能为空")
    @ApiModelProperty(value = "标签id")
    private String tagId;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "博客点击数")
    private Integer clickCount;

    @ApiModelProperty(value = "是否原创（0:不是 1：是）")
    private Integer isOriginal;

    @ApiModelProperty(value = "文章出处")
    private String articlesPart;

    @NotBlank(message = "博客分类ID不能为空")
    @ApiModelProperty(value = "博客分类ID")
    private String categoryId;

    @DecimalMax(value = "9999")
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "是否开启评论(0:否 1:是)")
    private Integer openComment;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Keyword)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}
