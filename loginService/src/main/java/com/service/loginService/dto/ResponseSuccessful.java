package com.service.loginService.dto;

import lombok.Data;

@Data
public class ResponseSuccessful {
    private int status;
    private String message;
    private Object data;

    public ResponseSuccessful( int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
