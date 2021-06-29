package com.hope.blog.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseVO   view object 表现层 基类对象
 *
 * Created by lijin on 2021/03/20.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseVO<T> extends PageInfo<T> {

    /**
     * 唯一ID
     */
    private String id;

}
