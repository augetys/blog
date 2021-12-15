package com.hope.blog.quartz.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.model.BlogArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

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
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private BlogArticleMapper blogArticleMapper;

    public void run(){
        log.info("run 执行成功");
    }

    public void run1(String str) {
        log.info("run1 执行成功，参数为： {}" + str);
    }

    public void run2() {
        log.info("像es中批量插入文章");
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        List<BlogArticle> list = blogArticleMapper.selectList(queryWrapper);
        elasticsearchRestTemplate.save(list, IndexCoordinates.of("blog"));
    }
}
