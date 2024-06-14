package com.cskaoyan.th58.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.host}")
    String host;

    @Value("${elasticsearch.port}")
    String port;

    @Bean
    public RestHighLevelClient restClient(){
        int intPort = Integer.parseInt(port);
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, intPort, "http"));
        return new RestHighLevelClient(builder);
    }
}
