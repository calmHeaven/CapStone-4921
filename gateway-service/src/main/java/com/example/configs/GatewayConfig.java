package com.example.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Removed @EnableHystrix annotation
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

@Bean
public RouteLocator routes(RouteLocatorBuilder builder){
    return builder.routes()
            .route("user-service", r->r.path("/users/**")
                    .filters(f->f.filter(filter))
                    .uri("lb://user-service"))
            // Public auth endpoints without authentication filter
            .route("auth-service-public", r->r.path("/auth/signup", "/auth/login")
                    .uri("lb://auth-service"))
            // Protected auth endpoints with authentication filter
            .route("auth-service-protected", r->r.path("/auth/**")
                    .filters(f->f.filter(filter))
                    .uri("lb://auth-service"))
            .build();
}
}