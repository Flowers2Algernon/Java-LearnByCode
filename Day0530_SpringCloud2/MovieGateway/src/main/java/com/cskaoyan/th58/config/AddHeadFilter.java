package com.cskaoyan.th58.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
public class AddHeadFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder builder = request.mutate();
        UUID uuid = UUID.randomUUID();
        ServerHttpRequest newRequest
                = builder.header("X-Request-TraceId", String.valueOf(uuid)).build();
        ServerWebExchange.Builder exchangeBuilder = exchange.mutate();
        ServerWebExchange newExchange = exchangeBuilder.request(newRequest).build();
        HttpMethod method = request.getMethod();
        URI uri = request.getURI();
        System.out.println(method+String.valueOf(uri)+String.valueOf(uuid));

        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
