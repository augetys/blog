package com.hope.blog;

import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.service.BlogArticleService;
import com.hope.blog.utils.DateUtil;
import com.hope.blog.utils.EsUtil;
import com.hope.blog.utils.MailUtil;
import com.hope.blog.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
@Ignore
@Slf4j
class BlogApplicationTests {

    @Resource
    private EsUtil esUtil;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {

    }

    @Test
    void createIndex() throws Exception {
        esUtil.createIndex("blog");
    }

    @Test
    void tests(){
        String s="中国|云南|昆明|电信";
        System.out.println(s.split("\\|")[2]);
    }

    @Test
    void deleteIndex() {
        esUtil.deleteIndex("blog");
    }

    @Test
    void getIndex() throws Exception {
        boolean blog = esUtil.isExist("blog");
        System.out.println(blog);
    }

    /**
     * spring data elasticsearch 方式
     *
     * @throws Exception
     */
    @Test
    void search() throws Exception {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 初始化分页参数
        // 可以发现，Elasticsearch中的分页是从第0页开始
        int page = 0;
        int pageSize = 3;
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "测试"))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withSourceFilter(new FetchSourceFilter(new String[]{"id", "title"}, new String[]{}))
                .withPageable(PageRequest.of(page, pageSize));
        //查询结果
        NativeSearchQuery searchQuery = queryBuilder.build();
        log.info("DSL:{}", searchQuery.getQuery().toString());
        // 执行搜索，获取结果c
        SearchHits<BlogArticle> blogArticleSearchHits = elasticsearchRestTemplate.search(searchQuery, BlogArticle.class);
        System.out.println(blogArticleSearchHits.getTotalHits());
        blogArticleSearchHits.forEach(searchHit -> System.out.println(searchHit.getContent()));
    }

    /**
     * RestHighLevelClient + SearchSourceBuilder方式
     *
     * @throws Exception
     */
    @Test
    void searchOne() throws Exception {
        //{
        //  "from": 0,
        //  "size": 3,
        //  "timeout": "2000ms",
        //  "query": {
        //    "match": {
        //      "title": {
        //        "query": "测试",
        //        "operator": "OR",
        //        "prefix_length": 0,
        //        "max_expansions": 50,
        //        "fuzzy_transpositions": true,
        //        "lenient": false,
        //        "zero_terms_query": "NONE",
        //        "auto_generate_synonyms_phrase_query": true,
        //        "boost": 1
        //      }
        //    }
        //  },
        //  "_source": {
        //    "includes": [
        //      "id",
        //      "title"
        //    ],
        //    "excludes": []
        //  },
        //  "sort": [
        //    {
        //      "createTime": {
        //        "order": "asc"
        //      }
        //    }
        //  ]
        //}
        // 1.创建并设置SearchSourceBuilder对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件--->生成DSL查询语句
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("部署", "title", "content", "summary"));
        // 第几页
        searchSourceBuilder.from(0);
        // 每页多少条数据
        searchSourceBuilder.size(3);
        // 获取的字段（列）和不需要获取的列
        searchSourceBuilder.fetchSource(new String[]{"id", "title"}, new String[]{});
        // 设置排序规则
        searchSourceBuilder.sort("createTime", SortOrder.ASC);
        // 设置超时时间为2s
        searchSourceBuilder.timeout(new TimeValue(2000));

        // 2.创建并设置SearchRequest对象
        SearchRequest searchRequest = new SearchRequest();
        // 设置request要搜索的索引和类型
        searchRequest.indices("blog");
        // 设置SearchSourceBuilder查询属性
        searchRequest.source(searchSourceBuilder);
        // 打印DSL
        log.info("DSL:{}", searchSourceBuilder.toString());
        // 3.查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    @Test
    public void setMail() {
        MailUtil.send("激活账号", "hahahahha", "1181881941@qq.com");
    }

    @Test
    public void getEnv() {
        String activeProfile = SpringContextHolder.getActiveProfile();
        System.out.println(activeProfile);
    }


    @Autowired
    private BlogArticleService blogArticleService;

    @Test
    public void randomDate() throws Exception {
        List<BlogArticle> list = blogArticleService.list();
        for (BlogArticle blogArticle : list) {
            blogArticle.setCreateTime(randomDate("2020-03-08", "2022-03-08"));
        }
        blogArticleService.saveOrUpdateBatch(list);
    }

    public static Date randomDate(String beginDate, String endDate) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}


