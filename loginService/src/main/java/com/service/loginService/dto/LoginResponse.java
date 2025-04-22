package com.service.loginService.dto;

import com.service.loginService.models.LoginEntity;

import lombok.Data;

@Data
public class LoginResponse {

    private String username;

    public LoginResponse(LoginEntity loginEntity){
        this.username = loginEntity.getUsername();
    }
}
