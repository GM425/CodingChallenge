package com.service.loginService.dto;

public class ErrorResponse {

    private int status;
    private String message;
    private String exception;

    public ErrorResponse(int status, String message, String exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }
}
