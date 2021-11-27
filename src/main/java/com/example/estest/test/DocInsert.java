package com.example.estest.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * ES插入索引信息
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class DocInsert {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");

        User user = new User();
        user.setName("张三");
        user.setSex("男");
        user.setAge(22);

        // 向ES输入数据，必须将数据转换成json格式
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        request.source(json, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        // 响应结果
        System.out.println(response.getResult());

        // 关闭ES客户端
        esClient.close();

    }

}
