package com.example.estest.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * ES创建索引
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class IndexCreate {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 创建索引
        CreateIndexRequest indexRequest = new CreateIndexRequest("user");
        CreateIndexResponse indexResponse = esClient.indices().create(indexRequest, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = indexResponse.isAcknowledged();
        System.out.println("索引操作：" + acknowledged);

        // 关闭ES客户端
        esClient.close();

    }

}
