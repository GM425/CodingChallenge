package com.service.loginService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.loginService.dto.LoginRequest;
import com.service.loginService.dto.LoginResponse;
import com.service.loginService.exceptions.UsernameTaken;
import com.service.loginService.models.LoginEntity;
import com.service.loginService.repository.LoginRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
// @RequiredArgsConstructor
public class LoginService {
   
    
	private final LoginRepository loginRepository;

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
        public LoginResponse createUser(LoginRequest loginRequest) throws UsernameTaken{
            Optional<LoginEntity> userPresent = loginRepository.findByUsername(loginRequest.getUsername());
            if (userPresent.isPresent()){
                throw new UsernameTaken("Username is already taken");
            }
            
            String password = loginRequest.getPassword();
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String passwordEncoded = encode.encode(password);
            loginRequest.setPassword(passwordEncoded);

            LoginEntity loginEntity = new LoginEntity(loginRequest);
            loginRepository.save(loginEntity);

            return new LoginResponse(loginEntity);
        }

        public List<LoginResponse> getAllUsers(){
            List<LoginEntity> users = loginRepository.findAll();
            return users.stream().map(LoginResponse::new).collect(Collectors.toList());
        }

        public LoginResponse getUserByUsername(LoginRequest loginRequest){
            String username = loginRequest.getUsername();
            Optional<LoginEntity> user = loginRepository.findByUsername(username);
            LoginEntity userExists = user.orElseThrow(() -> new EntityNotFoundException("Employee with username of " + username + " does not exist."));
            return new LoginResponse(userExists);
        }


    
}
