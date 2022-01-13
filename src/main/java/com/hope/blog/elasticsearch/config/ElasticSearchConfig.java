package com.hope.blog.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import org.springframework.scheduling.annotation.Scheduled;


/**
 * Created by lijin on  2021/9/14
 * https://blog.csdn.net/weixin_43549350/article/details/116322865
 * https://www.codetd.com/article/12139074
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {

//    @Bean
//    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
//        restClientBuilder = RestClient.builder(new HttpHost("110.42.244.186", 9200, "http"));
//        return new RestHighLevelClient(restClientBuilder.setHttpClientConfigCallback(requestConfig ->
//                requestConfig.setKeepAliveStrategy((response, context) -> TimeUnit.MINUTES.toMillis(3))));
//    }

    /**
     * springboot 整合 spring data elasticsearch 打印查询语句
     *
     * @return
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("110.42.244.186:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    /**
     * 可以尝试在 Spring boot 创建一个定时器定期探测下 es 保持 keepAlive
     */
    @Scheduled(fixedRate = 180000, initialDelay = 180000)
    public void keepConnectionAlive() {
        log.debug("Trying to ping Elasticsearch");
        try {
            boolean ping = restHighLevelClient().ping(RequestOptions.DEFAULT);
            log.debug("Ping succeeded for SportsFacilityViewRepository, it contains {} entities", ping);
        } catch (Exception e) {
            log.debug("Ping failed for SportsFacilityViewRepository");
        }
    }
}
