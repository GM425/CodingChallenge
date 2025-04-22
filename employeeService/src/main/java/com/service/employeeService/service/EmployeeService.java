package com.service.employeeService.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.service.employeeService.dto.EmployeeRequest;
import com.service.employeeService.dto.EmployeeResponse;

import com.service.employeeService.model.EmployeeEntity;
import com.service.employeeService.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employee = new EmployeeEntity(employeeRequest);
        employeeRepository.save(employee);
        
        logger.info("the employee with the employeeId {} has been created", employee.getId());
        return new EmployeeResponse(employee);

    }

    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeResponse::new).collect(Collectors.toList());
    }


    public Optional<EmployeeResponse> getEmployeeById(long id) {
        return employeeRepository.findById(id).map(EmployeeResponse::new);
    }

    public String deleteEmployee(long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception ex){
            logger.info("exception caught");
            System.out.println("There is no employee with that ID. Please try again");
        } finally {
            logger.info("finally ran");
        }
        return "the employee with the id of " + id + " has been deleted";
    }

    public EmployeeResponse updateCurrentEmployee(EmployeeRequest updatedEmployee, long id) {
        
        EmployeeEntity employee = employeeRepository.findById(id)
        .orElseThrow(() -> 
        new EntityNotFoundException("Employee with the id of " + id + " does not exist."));

        String username = updatedEmployee.getUsername();
        if (username != null && !employee.getUsername().equals(username)) {
            employee.setUsername(username);
        } 

        String firstName = updatedEmployee.getFirstName();
        if (firstName != null && !employee.getFirstName().equals(firstName)) {
            employee.setFirstName(firstName);
        }

        String lastName = updatedEmployee.getLastName();
        if (lastName != null && !employee.getLastName().equals(lastName)) {
            employee.setLastName(lastName);
        } 

        String streetAddress = updatedEmployee.getStreetAddress();
        if (streetAddress != null && !employee.getLastName().equals(streetAddress)) {
            employee.setStreetAddress(streetAddress);
        } 

        String city = updatedEmployee.getCity();
        String newString = "change";
        if (employee.getCity() != null){
            newString = employee.getCity();
        }
        if (city != null && !newString.equals(city)) {
            employee.setCity(city);
        }

        String state = updatedEmployee.getState();
        if (state != null && !employee.getState().equals(state)) {
            employee.setState(state);
        } 

        int zip = updatedEmployee.getZip();
        if (employee.getZip() != zip) {
            employee.setZip(zip);
        }

        String cellPhone = updatedEmployee.getCellPhone();
        if (cellPhone != null && !employee.getCellPhone().equals(cellPhone)) {
            employee.setCellPhone(cellPhone);
        } 

        String homePhone = updatedEmployee.getHomePhone();
        if (homePhone != null && !employee.getHomePhone().equals(homePhone)) {
            employee.setHomePhone(homePhone);
        }

        String email = updatedEmployee.getEmail();
        Optional<EmployeeEntity> emailOptional = employeeRepository.findByEmail(email);

        if (emailOptional.isPresent() && !email.equals(employee.getEmail())) {
            throw new IllegalStateException("This email is already taken");
        } else {
            employee.setEmail(email);
        }
    
        employeeRepository.save(employee);
        
        return new EmployeeResponse(employee);

    }

}
