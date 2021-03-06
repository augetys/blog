package com.hope.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.mapper.BlogArticleMapper;
import com.hope.blog.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.blog.service.BlogCategoryService;
import com.hope.blog.comment.model.BlogComment;
import com.hope.blog.comment.service.BlogCommentService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.CopyUtil;
import com.hope.blog.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Service
@Transactional
@Slf4j
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Resource
    private BlogArticleMapper blogArticleMapper;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogCommentService blogCommentService;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public IPage<BlogArticleListResponseDto> findListByPage(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTitle())) {
            queryWrapper.like("title", blogArticleSearchRequestDto.getTitle());
            queryWrapper.orderByAsc("sort");
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getCategoryId())) {
            queryWrapper.eq("category_id", blogArticleSearchRequestDto.getCategoryId());
            queryWrapper.orderByAsc("sort");
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getIsOriginal())) {
            queryWrapper.eq("is_original", blogArticleSearchRequestDto.getIsOriginal());
            queryWrapper.orderByAsc("sort");
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getIsPublish())) {
            queryWrapper.eq("is_publish", blogArticleSearchRequestDto.getIsPublish());
            queryWrapper.orderByAsc("sort");
        }
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getCreateTime())) {
            queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = '" + DateUtil.format(blogArticleSearchRequestDto.getCreateTime(), DateUtil.DATE_FORMAT_DAY) + "'");
            queryWrapper.orderByAsc("sort");
        }
        queryWrapper.orderByDesc("create_time");

        List<BlogArticle> list = blogArticleMapper.selectList(queryWrapper);
        List<BlogArticleListResponseDto> collect = CopyUtil.copyList(list, BlogArticleListResponseDto.class);
        if (!StringUtils.isEmpty(blogArticleSearchRequestDto.getTagId())) {
            collect = collect.stream().filter(item -> Arrays.asList(item.getTagId().split(",")).contains(blogArticleSearchRequestDto.getTagId())).sorted(Comparator.comparing(BlogArticleListResponseDto::getSort)).collect(Collectors.toList());
        }
        IPage<BlogArticleListResponseDto> pages = new Page<>();
        if (!CollectionUtils.isEmpty(collect)) {
            collect.forEach(
                    item -> {
                        item.setAuthor(item.getIsOriginal() == 1 ? sysUserService.getById(item.getCreateBy()).getUsername() : item.getArticleAuthor());
                        item.setCategoryName(blogCategoryService.getById(item.getCategoryId()).getName());
                        item.setCategoryIcon(blogCategoryService.getById(item.getCategoryId()).getIcon());
                        QueryWrapper<BlogComment> queryWrapper1 = new QueryWrapper<>();
                        queryWrapper1.eq("article_id", item.getId());
                        item.setCommentCount(blogCommentService.count(queryWrapper1));
                    }
            );
            pages = CommonPage.getPages(blogArticleSearchRequestDto.getPageNum(), blogArticleSearchRequestDto.getPageSize(), collect);
        }
        return pages;
    }

    @Override
    public List<BlogArticle> getHotArticle() {
        return blogArticleMapper.getHotArticle();
    }

    @Override
    public IPage<BlogArticleListResponseDto> keyword(BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 初始化分页参数
        // 可以发现，Elasticsearch中的分页是从第0页开始
        // int page = 0;
        // int pageSize = 3;
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(blogArticleSearchRequestDto.getKeyword(), "title", "content", "summary"))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(blogArticleSearchRequestDto.getPageNum(), blogArticleSearchRequestDto.getPageSize()));
        // 查询结果
        NativeSearchQuery searchQuery = queryBuilder.build();
        // 执行搜索，获取结果
        SearchHits<BlogArticleListResponseDto> blogArticleSearchHits = elasticsearchRestTemplate.search(searchQuery, BlogArticleListResponseDto.class);
        List<SearchHit<BlogArticleListResponseDto>> searchHits = blogArticleSearchHits.getSearchHits();
        List<BlogArticleListResponseDto> results = new ArrayList<>();
        searchHits.forEach(
                item -> {
                    results.add(item.getContent());
                }
        );
        if (!CollectionUtils.isEmpty(results)) {
            results.forEach(
                    item -> {
                        item.setAuthor(sysUserService.getById(item.getCreateBy()).getUsername());
                        item.setCategoryName(blogCategoryService.getById(item.getCategoryId()).getName());
                        item.setCategoryIcon(blogCategoryService.getById(item.getCategoryId()).getIcon());
                    }
            );
        }
        IPage<BlogArticleListResponseDto> page = new Page<>();
        page.setRecords(results);
        page.setTotal(blogArticleSearchHits.getTotalHits());
        page.setSize(blogArticleSearchRequestDto.getPageSize());
        page.setPages(blogArticleSearchRequestDto.getPageNum());
        return page;
    }
}
