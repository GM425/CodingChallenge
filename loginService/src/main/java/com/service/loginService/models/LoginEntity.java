package com.service.loginService.models;

import jakarta.persistence.Table;

import com.service.loginService.dto.LoginRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.service.loginService.config.configBean;

@Entity
@Data
@Table(name="login_db")
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false, table = "login_db")
    private long id;
    
    @Column(name="email", unique = true, table = "login_db")
    private String username;

    @Column(name="password", unique = false, table = "login_db")
    private String password;

    public LoginEntity(LoginRequest loginRequestDTO){
        this.username = loginRequestDTO.getUsername();
        this.password = loginRequestDTO.getPassword();
    }
    
}




