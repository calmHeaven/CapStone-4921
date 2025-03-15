package abe.gateway;

import com.example.AuthApplication;
import com.example.DiscoveryService;
import com.example.GatewayApplication;
import com.example.UserApplication;
import org.abe.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class APIGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                DiscoveryService.class, args
        );        SpringApplication.run(
                ConfigService.class, args
        );        SpringApplication.run(
                AuthApplication.class, args
        );        SpringApplication.run(
                GatewayApplication.class, args
        );        SpringApplication.run(
                UserApplication.class, args
        );
    }

}
