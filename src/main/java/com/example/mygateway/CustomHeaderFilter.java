package com.example.mygateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomHeaderFilter implements GatewayFilterFactory<CustomHeaderFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey("spring-cloud-course")) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
            exchange.getRequest().mutate()
                    .headers(headers -> headers.remove("spring-cloud-course"));
            return chain.filter(exchange);
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config{

    }
}