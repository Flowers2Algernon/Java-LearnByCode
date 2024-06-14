package com.cskaoyan.th58.es;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.th58.es.dao.Item;
import com.cskaoyan.th58.es.dao.PriceAggregationInfo;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SearchTest {
    @Autowired
    RestHighLevelClient client;

    @Test
    public void matchAllQuery() throws IOException {
        //构造查询请求Request对象
        SearchRequest searchRequest = new SearchRequest("product");

        //构造SearchSOurceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //构造具体的查询
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        //设置具体的查询
        searchSourceBuilder.query(matchAllQueryBuilder);

        //设置from,size
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(100);

        //设置具体的请求参数
        searchRequest.source(searchSourceBuilder);

        //发起查询请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //解析响应，获取查询到的文档数据
        ArrayList<Item> items = new ArrayList<>();

        SearchHits searchHits = searchResponse.getHits();
        //获取满足查询条件的文档总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("total"+total);

        //获取满足查询条件的文档
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            //获取hit对象的source属性值
            String sourceAsString = hit.getSourceAsString();
            //将json转换为item对象
            Item item = JSON.parseObject(sourceAsString, Item.class);
            items.add(item);
        }
        System.out.println(items);
    }

    @Test
    public void termQuery() throws IOException {
        //构造查询请求
        SearchRequest searchRequest = new SearchRequest("product");

        //准备searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //构造具体查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "充电器");

        //设置查询
        searchSourceBuilder.query(termQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        //发起查询请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Item> items = new ArrayList<>();

        //解析响应
        SearchHits searchHits = searchResponse.getHits();

        //获取满足文档的总条数
        long length = Objects.requireNonNull(searchHits.getTotalHits()).value;
        System.out.println("total"+length);

        //获取查询到的文档
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            //获取原始文档的json字符串
            String json = hit.getSourceAsString();
            Item item = JSON.parseObject(json, Item.class);
            items.add(item);
        }
        System.out.println(items);
    }

    @Test
    public void matchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手机充电器");
        //设置运算符
        matchQueryBuilder.operator(Operator.AND);

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Item> items = new ArrayList<>();

        SearchHits searchHits = searchResponse.getHits();

        long value = searchHits.getTotalHits().value;
        System.out.println("total"+value);

        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Item item = JSON.parseObject(sourceAsString, Item.class);
            items.add(item);
        }
        System.out.println(items);
    }

    @Test
    public void queryStringQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("安全 AND 充电器").field("title").field("sellPoint");

        searchSourceBuilder.query(queryStringQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Item> items = new ArrayList<>();

        SearchHits searchHits = searchResponse.getHits();
        long value = searchHits.getTotalHits().value;
        System.out.println("total: "+value);
        SearchHit[] hits = searchHits.getHits();

        for (SearchHit hit : hits) {
            //将json转换为item对象
            String json = hit.getSourceAsString();
            Item item = JSON.parseObject(json, Item.class);
            items.add(item);
        }
        System.out.println(items);
    }

    @Test
    public void rangeAndSortQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gt(60).lt(1000);

        searchSourceBuilder.query(rangeQueryBuilder);

        //设置排序
        searchSourceBuilder.sort("price", SortOrder.DESC);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();

        long value = searchHits.getTotalHits().value;
        System.out.println("total"+value);

        SearchHit[] hits = searchHits.getHits();
        ArrayList<Item> items = new ArrayList<>();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Item item = JSON.parseObject(sourceAsString, Item.class);
            items.add(item);
        }
        System.out.println(items);
    }

    @Test
    public void boolQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //1.must 充电器
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "充电器");
        boolQueryBuilder.must(matchQueryBuilder);

        //2.should  原装
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "原装");
        boolQueryBuilder.should(termQueryBuilder);

        //3. filter 1- 100
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gt(20).lt(100);
        boolQueryBuilder.filter(rangeQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析响应
        SearchHits searchHits = searchResponse.getHits();

        // 获取满足文档的总条数
        long value = searchHits.getTotalHits().value;
        System.out.println(value);

        ArrayList<Item> items = new ArrayList<>();
        // 获取查询到的文档
        SearchHit[] hits = searchHits.getHits();

        for ( SearchHit hit: hits) {

            // 获取原始文档对应json字符串
            String docJson = hit.getSourceAsString();
            Item item = JSON.parseObject(docJson, Item.class);

            items.add(item);

        }

        System.out.println(items);

    }

    //指标值聚合
    @Test
    public void valueAggs() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //构造具体查询
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        searchSourceBuilder.query(matchAllQueryBuilder);

        //构造聚合函数
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("max_price").field("price");
        //设置聚合函数
        searchSourceBuilder.aggregation(maxAggregationBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //根据聚合名称来获取最大指标聚合结果
        Aggregations aggregations = searchResponse.getAggregations();
        Max maxPrice = aggregations.get("max_price");

        double maxPriceValue = maxPrice.getValue();
        System.out.println(maxPriceValue);
    }

    @Test
    public void bucketsAggs() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte(100).lte(200);

        searchSourceBuilder.query(rangeQueryBuilder);

        //构造聚合函数
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("price_bucket").field("price");
        //设置聚合函数
        searchSourceBuilder.aggregation(termsAggregationBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //根据聚合名称获取最大指标值聚合结果
        Aggregations aggregations = searchResponse.getAggregations();
        Terms priceBucket = aggregations.get("price_bucket");

        List<? extends Terms.Bucket> buckets = priceBucket.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            double value = bucket.getKeyAsNumber().doubleValue();
            long docCount = bucket.getDocCount();
            System.out.println("key: " + value + " ----- value: " + docCount);
        }

    }
    @Test
    public void innerBucket() throws IOException {
        //嵌套聚合查询
        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //构造具体查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte(100).lte(1000);

        //设置查询
        searchSourceBuilder.query(rangeQueryBuilder);

        //构造聚合函数
        //1， 构造外层分桶聚合函数
        TermsAggregationBuilder outerAggBuckets = AggregationBuilders.terms("price_bucket").field("price");

        //构造内层分桶聚合函数
        TermsAggregationBuilder innerAggBuckets = AggregationBuilders.terms("tm_buckets").field("tmName");

        //给外层分桶聚合设置对应的内层分桶聚合
        outerAggBuckets.subAggregation(innerAggBuckets);

        //设置聚合参数
        searchSourceBuilder.aggregation(outerAggBuckets);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();

        //根据聚合名称获取最大指标值聚合结果
        Terms priceBucket = aggregations.get("price_bucket");

        //获取所有分桶
        List<? extends Terms.Bucket> outerBuckets = priceBucket.getBuckets();

        //结果集
        ArrayList<PriceAggregationInfo> result = new ArrayList<>();

        //遍历外层分桶
        for (Terms.Bucket outerBucket : outerBuckets) {
            PriceAggregationInfo priceAggregationInfo = new PriceAggregationInfo();
            double value = outerBucket.getKeyAsNumber().doubleValue();
            priceAggregationInfo.setPrice(value);
            long outerBucketDocCount = outerBucket.getDocCount();

            //从外层同中获取内层分桶聚合的结果
            Aggregations innerAgg = outerBucket.getAggregations();
            Terms tmBucket = innerAgg.get("tm_bucket");

            ArrayList<String> tmList = new ArrayList<>();
            List<? extends Terms.Bucket> tmBucketBuckets = tmBucket.getBuckets();
            for (Terms.Bucket tmBucketBucket : tmBucketBuckets) {
                //获取内层桶的key
                String keyAsString = tmBucketBucket.getKeyAsString();
                long docCount = tmBucketBucket.getDocCount();
                tmList.add(keyAsString);
            }
            priceAggregationInfo.setTrademarkNames(tmList);
            result.add(priceAggregationInfo);

        }
        System.out.println(result);
    }
}
