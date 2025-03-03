package com.abe.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("app", r -> r.path("/user/**")
//                        .filters(f->f
//                                        .prefixPath("/")
//                                        .addResponseHeader("X-Powered-By", "StayOnTrack API Gateway Service")
//                        )
//                        .uri("http://localhost:8081")
//                )
//                .route("auth", r -> r.path("/auth/**")
//                        .filters(f->f
//                                        .prefixPath("/")
//                                        .addResponseHeader("X-Powered-By", "StayOnTrack API Gateway Service")
//                        )
//                        .uri("http://localhost:8081")
//                )
//                .build();
//    }

}
