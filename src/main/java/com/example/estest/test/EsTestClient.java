package com.example.estest.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * ES客服端对象创建
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class EsTestClient {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 关闭ES客户端
        esClient.close();

    }

}
