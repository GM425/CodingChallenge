package com.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.service.dtos.RegistrationRequest;
import com.service.dtos.RegistrationResponse;
import com.service.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    } 

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String getHealth(){
        return "Healthy";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody RegistrationRequest registrationRequest){
        registrationService.createEmployee(registrationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RegistrationResponse> getAllEmployees(){
        return registrationService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationResponse getEmployeeId(@PathVariable long id){
        Optional<RegistrationResponse> employee = registrationService.getEmployeeById(id);
        return employee.get();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable long id){
        Optional<RegistrationResponse> newRegistration = registrationService.getEmployeeById(id);
        if (newRegistration.isPresent()){
            return registrationService.deleteEmployee(id);
        } else {
            return "there is no user with that Id";
        }
    }

    @PatchMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationResponse updateEmployee(@RequestBody RegistrationRequest registrationRequest, @PathVariable long id){
        return registrationService.updateCurrentEmployee(registrationRequest, id);
    }
}
