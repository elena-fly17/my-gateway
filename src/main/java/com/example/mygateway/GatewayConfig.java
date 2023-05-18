package com.example.mygateway;

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
                .route("route1", r -> r.path("/api/client/**")
                        .filters(f -> f.filter(customHeaderFilter
                                .apply(new CustomHeaderFilter.Config())))
                        .uri("lb://client-service"))
                .route("route2", r -> r.path("/api/update/**")
                        .filters(f -> f.filter(customHeaderFilter
                                .apply(new CustomHeaderFilter.Config())))
                        . uri("lb://client-update"))
                .build();
    }
}