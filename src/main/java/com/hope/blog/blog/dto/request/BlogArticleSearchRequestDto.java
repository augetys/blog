package com.hope.blog.blog.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/7/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogArticleSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "分类名")
    private String categoryKeyword;
    @ApiModelProperty(value = "分类Id")
    private String categoryId;
    @ApiModelProperty(value = "是否原创")
    private String originalKeyword;
    @ApiModelProperty(value = "是否发布")
    private String publishKeyword;
    @ApiModelProperty(value = "标签名")
    private String tagKeyword;
    @ApiModelProperty(value = "发布时间")
    private String createTime;
}
