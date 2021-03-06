package com.hope.blog.quartz.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.service.BlogCategoryService;
import com.hope.blog.common.security.config.AuthUserDetails;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试用
 *
 * @author lijin
 * @date 2019-01-08
 */
@Slf4j
@Component
public class TestTask {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private BlogCategoryService blogCategoryService;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private BlogArticleMapper blogArticleMapper;

    public void run() {
        log.info("run 执行成功");
    }

    public void run1(String str) {
        log.info("run1 执行成功，参数为： {}" + str);
    }

    public void run2() {
        log.info("run2 执行成功");
        // 此处获取SecurityContextHolder是无法获取到的
        AuthUserDetails authUserDetails = (AuthUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(authUserDetails);
    }

    public void run3() {
        log.info("开始删除blog索引");
        elasticsearchRestTemplate.indexOps(BlogArticleListResponseDto.class).delete();
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        List<BlogArticle> list = blogArticleMapper.selectList(queryWrapper);
        List<BlogArticleListResponseDto> response = CopyUtil.copyList(list, BlogArticleListResponseDto.class);
        if (!CollectionUtils.isEmpty(response)){
            response.forEach(
                    item -> {
                        item.setAuthor(sysUserService.getById(item.getCreateBy()).getUsername());
                        item.setCategoryName(blogCategoryService.getById(item.getCategoryId()).getName());
                        item.setCategoryIcon(blogCategoryService.getById(item.getCategoryId()).getIcon());
                    }
            );
        }
        log.info("开始创建blog索引");
        elasticsearchRestTemplate.indexOps(BlogArticleListResponseDto.class).create();
        elasticsearchRestTemplate.putMapping(BlogArticleListResponseDto.class);
        log.info("开始插入数据");
        elasticsearchRestTemplate.save(response);
    }
}
