package com.service.loginService.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.loginService.models.LoginEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

    // @JsonCreator
    // public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password){
    //     this.username = username;
    //     this.password = password;
    // }
}
