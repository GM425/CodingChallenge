package com.service.loginService.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.service.loginService.models.LoginEntity;
import com.service.loginService.repository.LoginRepository;


public class UserDetailsServiceImp implements UserDetailsService {
    
    @Autowired
    private LoginRepository loginRepository;

    // public UserDetailsServiceImp(LoginRepository loginRepository) {
    //     this.loginRepository = loginRepository;
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginEntity> loginEntity = loginRepository.findByUsername(username);
        return loginEntity.map(UserDetailsClass::new)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }



}
