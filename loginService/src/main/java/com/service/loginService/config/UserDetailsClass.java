package com.service.loginService.config;

import java.util.Collection;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.context.annotation.Configuration;

import com.service.loginService.models.LoginEntity;

// import lombok.Data;




public class UserDetailsClass implements UserDetails{

    private String username;
    private String password;

    public UserDetailsClass(LoginEntity loginEntity) {
        this.username = loginEntity.getUsername();
        this.password = loginEntity.getPassword();
    }

    // Implement the UserDetailsService interface method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    

}
