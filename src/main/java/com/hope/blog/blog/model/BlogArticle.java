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

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author lijin
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
@ApiModel(value="BlogArticle对象", description="博客文章表")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "博客简介")
    private String summary;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "是否发布：0：否，1：是")
    private Integer isPublish;

    @ApiModelProperty(value = "标签id")
    private String tagId;

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

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "是否开启评论(0:否 1:是)")
    private Boolean openComment;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}