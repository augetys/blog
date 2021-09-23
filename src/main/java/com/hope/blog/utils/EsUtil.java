package com.hope.blog.utils;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;


/**
 * Created by lijin on  2021/9/14
 */
@Component
public class EsUtil {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 批量创建索引
     *
     * @param indexs 索引名称
     */
    public void createIndex(String... indexs) throws Exception {
        for (String index : indexs) {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            if (restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) {
                throw new RuntimeException("索引:" + index + "已存在");
            }
        }
        for (String index : indexs) {
            // 创建索引请求
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
            // 创建执行请求
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println(JSONObject.toJSONString(createIndexResponse));
        }
    }

    /**
     * 查看索引是否存在
     *
     * @return true存在 false不存在
     * @throws IOException es连接异常
     */
    public boolean getIndex(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     *
     */
    public void deleteIndex(String index) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        try {
            AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            System.out.println(JSONObject.toJSONString(deleteIndexResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
