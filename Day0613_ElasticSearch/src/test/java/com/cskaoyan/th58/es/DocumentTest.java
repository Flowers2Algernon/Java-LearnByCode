package com.cskaoyan.th58.es;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.th58.es.dao.Person;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DocumentTest {
    @Autowired
    RestHighLevelClient client;

    @Test
    public void addDocumentWithMap() throws IOException {
        //1. 构建添加文档的请求
        IndexRequest indexRequest = new IndexRequest("58th");

        //2. 准备文档的属性值，封装到map中
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("id", 1);
        docMap.put("name", "kongling");
        docMap.put("age", 18);

        //将map对象放入到request中
        indexRequest.source(docMap);
        //指定文档的唯一标识
        indexRequest.id("cskaoyan001");

        //发起请求
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    @Test
    public void addDocumentWithObj() throws IOException {
        //1. 准备文档数据
        Person person = new Person();
        person.setId(2);
        person.setName("yuanzhi");
        person.setAge(19);

        //2. 将对象转换为JSON字符串
        Object json = JSON.toJSON(person);

        //3. 创建添加文档的请求
        IndexRequest request = new IndexRequest("58th").id("cskaoyan002").source(json, XContentType.JSON);

        // 4. 发起请求
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.status());


    }

    @Test
    public void updateAll() throws IOException {
        //1. 构建文档的添加请求
        IndexRequest indexRequest = new IndexRequest("58th");

        // 准备文档的各个属性值，封装到Map对象中
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("id", 1);
        docMap.put("name", "jingtian");
        docMap.put("age", 100);

        //向request对象中放入map
        indexRequest.source(docMap);
        //指定文档的唯一标识
        indexRequest.id("cskaoyan001");

        //发起请求
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    @Test
    public void updatePartial() throws IOException {
        // 1. 准备修改的字段值
        HashMap<String, Object> updateMap = new HashMap<>();
        // 放入要修改的字段名和字段值
        updateMap.put("name", "changfeng");

        //构造请求对象
        UpdateRequest updateRequest = new UpdateRequest("58th", "cskaoyan002");
        //设置修改的属性值
        updateRequest.doc(updateMap);

        //发起请求
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }
}
