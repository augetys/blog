package com.hope.blog.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * PageVO  用于分页
 * <p>
 * Created by lijin on  2021/6/8
 */
@Data
public class PageInfo {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;
}
