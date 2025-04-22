package com.service.employeeService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

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
  
}
