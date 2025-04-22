package com.service.loginService.exceptions;

public class UsernameTaken extends Exception {
    public UsernameTaken(String message){
        super(message);
    }
    // public UsernameTaken(String message, Throwable cause){
    //     super(message,cause);
    // }
}
