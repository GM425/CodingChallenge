package com.service.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class GatewayValidator {


    public static final List<String> openApiEndpoints = List.of(
        "/gateway/health",
        "/employee/health",
            "/login/register",
            "/login/login"




);

public Predicate<ServerHttpRequest> isSecured =
        request -> openApiEndpoints
                .stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
