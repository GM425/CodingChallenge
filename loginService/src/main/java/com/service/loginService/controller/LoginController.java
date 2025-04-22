package com.service.loginService.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import com.service.loginService.config.securityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.service.loginService.dto.ErrorResponse;
import com.service.loginService.dto.LoginRequest;
import com.service.loginService.dto.LoginResponse;
import com.service.loginService.dto.ResponseSuccessful;
import com.service.loginService.dto.ResponseTokenCreated;
import com.service.loginService.dto.UsernameDup;
import com.service.loginService.exceptions.UsernameTaken;
import com.service.loginService.jwt.jwtSetup;
import com.service.loginService.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final LoginService loginService;
    private final jwtSetup jwt;

    public LoginController(LoginService loginService, jwtSetup jwt) {
        this.loginService = loginService;
        this.jwt = jwt;
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String getHealth(){
        return "Healthy";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LoginResponse> getAllUsers(){
        return loginService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createNewUser(@RequestBody LoginRequest loginRequest){
        
       try {
            loginService.createUser(loginRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                new ResponseSuccessful(201, "User Created Successfully", loginRequest.getUsername())
            );
       } catch (UsernameTaken exception){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                new UsernameDup(409, "Duplicate Username", exception.getMessage())
            );
       }
    }

    @GetMapping("/{username}")
    public void userLogin(){

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        
        jwtSetup jwtSetupInstance = new jwtSetup();
        jwtSetupInstance.validateToken(token);
        return "Token is Valid";

    }

    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String token = jwt.generateToken(loginRequest);

            return ResponseEntity.status(HttpStatus.OK)
            .header( "Authorization", "Bearer " + token)
            .body(new ResponseTokenCreated(
                200,
                "Token Generated Bearer "
                // + token
            ));

        } catch(AuthenticationException exception){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(401, "Invalid Username or Password", exception.getMessage()));

            
            
        }
    }

}
