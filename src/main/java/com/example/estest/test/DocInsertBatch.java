package com.example.estest.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

/**
 * ES批量插入索引信息
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class DocInsertBatch {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "张三", "age", 22, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "李四", "age", 23, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "王五", "age", 24, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "赵六", "age", 25, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "孙七", "age", 26, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "周八", "age", 27, "sex", "男"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        // 响应结果
        System.out.println(response.getIngestTookInMillis());
        System.out.println(Arrays.toString(response.getItems()));

        // 关闭ES客户端
        esClient.close();

    }

}
