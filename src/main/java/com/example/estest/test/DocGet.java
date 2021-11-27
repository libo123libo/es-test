package com.example.estest.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * ES获取索引信息
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class DocGet {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 获取数据
        GetRequest request = new GetRequest();
        request.index("user").id("1001");

        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        // 响应结果
        System.out.println(response.getSourceAsString());

        // 关闭ES客户端
        esClient.close();

    }

}
