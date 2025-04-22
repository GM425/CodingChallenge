package com.service.employeeService.dto;

import com.service.employeeService.model.EmployeeEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private String homePhone;
    private String cellPhone;
    private String email;

    public EmployeeResponse(EmployeeEntity employeeEntity) {
        this.id = employeeEntity.getId();
        this.username = employeeEntity.getUsername();
        this.firstName = employeeEntity.getFirstName();
        this.lastName = employeeEntity.getLastName();
        this.streetAddress = employeeEntity.getStreetAddress();
        this.city = employeeEntity.getCity();
        this.state = employeeEntity.getState();
        this.zip = employeeEntity.getZip();
        this.cellPhone = employeeEntity.getCellPhone();
        this.homePhone = employeeEntity.getHomePhone();
        this.email = employeeEntity.getEmail();
    }
}
