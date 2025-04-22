package com.service.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/gateway")
public class GatewayHealth {

    @GetMapping("/health")
    public String status() {
        return "API Gateway is healthy!";
    }

}
