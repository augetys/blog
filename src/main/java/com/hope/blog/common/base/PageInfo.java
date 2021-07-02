package com.hope.blog.common.base;

import lombok.Data;

/**
 * PageVO  用于分页
 *
 * Created by lijin on  2021/6/8
 */
@Data
public class PageInfo {


    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;
}
