package com.hope.blog.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;


/**
 * Created by lijin on  2021/9/14
 */
@Configuration
public class ElasticSearchConfig {
    //    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        return new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("110.42.244.186", 9200, "http")
//                )
//        );
//    }
    private final long MAX_KEEP_ALIVE_MINUTES = 1;

    /**
     * springboot 整合 spring data elasticsearch 打印查询语句
     * https://blog.csdn.net/weixin_43549350/article/details/116322865
     * https://www.codetd.com/article/12139074
     * @return
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("110.42.244.186:9200")
                .withConnectTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
