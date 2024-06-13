package com.cskaoyan.th58.es;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IndexTest {
    @Autowired
    RestHighLevelClient client;

    @Test
    public void createIndex() throws IOException {
        //1. 获取专门访问索引的index对象
        IndicesClient indices = client.indices();

        //2. 构造创建索引的请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("58th");

        //3. 发出创建索引的请求
        CreateIndexResponse response = indices.create(createIndexRequest, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());
    }

    @Test
    public void creatIndexAndMapping() throws IOException {
        //1. 获取索引访问的client对象
        IndicesClient indices = client.indices();

        //2. 构造创建索引的请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("58th");

        //3. 添加映射参数
        String mappingString = " {\n" +
                "    \"properties\": {\n" +
                "      \"id\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"name\": {\n" +
                "        \"type\": \"text\"\n" +
                "      }, \n" +
                "      \"age\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        createIndexRequest.mapping(mappingString, XContentType.JSON);

        //4. 发起请求
        CreateIndexResponse response = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    //查询索引
    @Test
    public void getIndex() throws IOException {
        //1. 获取访问索引的client对象
        IndicesClient indices = client.indices();

        //2. 构造查询索引的请求
        GetIndexRequest getIndexRequest = new GetIndexRequest("58th");

        //发起请求
        GetIndexResponse getIndexResponse = indices.get(getIndexRequest, RequestOptions.DEFAULT);

        //解析相应，得到json数据
        Map<String, MappingMetadata> mappings = getIndexResponse.getMappings();
        for (Map.Entry<String, MappingMetadata> entry : mappings.entrySet()) {
            System.out.println("index: " + entry.getKey() + "---" + entry.getValue().getSourceAsMap());
        }


    }

    @Test
    public void exist() throws IOException {
        //判断索引是否存在
        //1. 先访问索引的client对象
        IndicesClient indices = client.indices();

        //2. 构建查询索引的请求
        GetIndexRequest getIndexRequest = new GetIndexRequest("58th");

        //3. 发起请求
        boolean exists = indices.exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    public void delete() throws IOException {
        //1. 获取访问索引的client对象
        IndicesClient indices = client.indices();

        //2. 构建删除索引的请求
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("58th");

        //3. 发送请求
        AcknowledgedResponse delete = indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }
}
