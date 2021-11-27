package com.example.estest.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * ES查询索引信息
 *
 * @author libo
 * @date 2021/11/8 11:03
 */
public class DocQuery {

    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        // 1.查询索引中全部的数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 2.条件查询 termQuery
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        // 年龄等于22的数据
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age","22")));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 3.分页查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        // 查询所有数据前两条
//        // (当前页码-1)*每页显示数据条数
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).from(0).size(2));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 4.排序查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        // 按照年龄降序查询所有数据
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).sort("age", SortOrder.DESC));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 5.过滤字段查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        // includes为需要查询的字段
//        String[] includes = {"name"};
//        // excludes为不需要查询的字段
//        String[] excludes = {"age"};
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).fetchSource(includes, excludes));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 6.组合查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        // 查询name包含“张三”的数据，分词查询
////        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "张三"));
//        // 查询sex包含“男”的数据，分词查询
////        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "女"));
//
//        // 同“or”操作
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 22));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 23));
//
//        builder.query(boolQueryBuilder);
//
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 7.范围查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//
//        // 查询年龄age≥25并且age≤26的数据
//        rangeQuery.gte(25);
//        rangeQuery.lte(26);
//
//        builder.query(rangeQuery);
//
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        // 响应结果
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 7.模糊查询
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "王五").fuzziness(Fuzziness.ONE));

        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 响应结果
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        // 关闭ES客户端
        esClient.close();

    }

}
