package com.hope.blog.blog.mapper;

import com.hope.blog.blog.model.BlogWebsite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 网址导航表 Mapper 接口
 * </p>
 *
 * @author lijin
 * @since 2021-12-29
 */
public interface BlogWebsiteMapper extends BaseMapper<BlogWebsite> {
   List<BlogWebsite> findByCategoryId(Integer categoryId);
}
