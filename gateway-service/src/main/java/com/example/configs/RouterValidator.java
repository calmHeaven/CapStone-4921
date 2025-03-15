package com.example.configs;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {
    public static final List<String> publicEndpoints = List.of(
            "/auth/signup"
    );
    public Predicate<ServerHttpRequest> areSecureEndpoints =
            request -> publicEndpoints.stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));
}
