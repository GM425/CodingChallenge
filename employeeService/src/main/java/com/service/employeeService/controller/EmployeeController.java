package com.service.employeeService.controller;

// import org.hibernate.mapping.List;
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

import com.service.employeeService.dto.EmployeeRequest;
import com.service.employeeService.dto.EmployeeResponse;
import com.service.employeeService.service.EmployeeService;

//controller is like api routes in express 
@RestController
@RequestMapping("/employee")
// @RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    } 

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String getHealth(){
        return "Healthy";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeService.createEmployee(employeeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeId(@PathVariable long id){
        Optional<EmployeeResponse> employee = employeeService.getEmployeeById(id);
        return employee.get();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable long id){
        Optional<EmployeeResponse> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()){
            return employeeService.deleteEmployee(id);
        } else {
            return "there is no user with that Id";
        }
    }

    @PatchMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable long id){
        return employeeService.updateCurrentEmployee(employeeRequest, id);
    }



   


}

//at about 9 to 10 minute creating DTO Data transfer object to pass in when doing an api request, so that dto is sent back in response instead of the record from the database.