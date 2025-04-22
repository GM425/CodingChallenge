package com.service.loginService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernameDup {

    private int status;
    private String message;
    private Object data;

}
