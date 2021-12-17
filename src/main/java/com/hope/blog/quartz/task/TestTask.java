package com.hope.blog.quartz.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.service.BlogCategoryService;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
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
    }

    public void run3() throws Exception {
        log.info("开始删除blog索引");
        elasticsearchRestTemplate.indexOps(IndexCoordinates.of("blog")).delete();
        log.info("向es中同步文章");
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
        // 会自动创建索引
        elasticsearchRestTemplate.save(response, IndexCoordinates.of("blog"));
    }
}
