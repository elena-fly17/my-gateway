package com.example.mygateway;

import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder,
                                           CustomHeaderFilter customHeaderFilter) {
        return routeLocatorBuilder.routes()
                .route("route1", r -> r.path("/api/client")
                        .filters(f -> f.filter(customHeaderFilter
                                .apply(new CustomHeaderFilter.Config())))
                        .uri("http://localhost:8082")).build();
    }
}