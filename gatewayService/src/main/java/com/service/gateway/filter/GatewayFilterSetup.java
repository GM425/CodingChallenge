package com.service.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.service.gateway.dto.ErrorResponse;
import com.service.gateway.jwt.GatewayJwtSetup;

import reactor.core.publisher.Mono;

@Component
public class GatewayFilterSetup extends AbstractGatewayFilterFactory<GatewayFilterSetup.Config>{
    
    private static final Logger logger = LoggerFactory.getLogger(GatewayFilterSetup.class);
    
    @Autowired
    private GatewayJwtSetup jwtSetup;
    
    private final GatewayValidator validator;
    
    public GatewayFilterSetup(GatewayValidator validator){
        super(Config.class);
        this.validator = validator;
    }

    public static class Config {
    }
    // ex. overrides subclasses
    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                    if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                        logger.warn("Missing Authorization header");
                        return handleError(exchange, "No Authorization Header");
                    }

                    String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);


                    if (token != null && token.startsWith("Bearer ")) {
                        token = token.substring(7);      
                    }



                    try {
                        jwtSetup.validateToken(token);
                    } catch (Exception errorResponse){
                        logger.warn("Invalid Token");
                        return handleError(exchange, "Invalid access token");
                    }
        
            }
            return chain.filter(exchange);
        };
    }

    public Mono<Void> handleError(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");

        ErrorResponse errorResponse = new ErrorResponse(401, "Unauthorized", message);

        byte[] errorResponseBytes = errorResponse.convertJson().getBytes();

        Mono<DataBuffer> body = Mono.just(response.bufferFactory().wrap(errorResponseBytes));

        return response.writeWith(body)
                .flatMap(aVoid -> Mono.error(new RuntimeException(message)));
    }

}

