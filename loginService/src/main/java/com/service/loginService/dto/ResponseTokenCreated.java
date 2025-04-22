package com.service.loginService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTokenCreated {
    
    private int status;
    private String message;

}
