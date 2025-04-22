package com.service.loginService.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImp();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DataSource dataSource) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login/register", "/login/login", "/login/validate", "/login/health", "/registration/**").permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // @Bean
    // public DataSource dataSource() { 
    //     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(); 
          
    //     driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/gfgspringsecuritydemo"); 
    //     driverManagerDataSource.setUsername("root"); 
    //     driverManagerDataSource.setPassword("143@Arpilu"); 
    //     driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
          
    //     return driverManagerDataSource; 
    // } 


}
