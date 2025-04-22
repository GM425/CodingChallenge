package com.service.models;

import com.service.dtos.RegistrationRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "username", unique = true, table = "registration_data")
    private String username;

    @Column(name = "firstname", unique = false, table = "registration_data")
    private String firstName;

    @Column(name = "lastname",  unique = false, table = "registration_data")
    private String lastName;

    @Column(name = "streetaddress", unique = true, table = "registration_data")
    private String streetAddress;

    @Column(name = "city",  unique = false, table = "registration_data")
    private String city;

    @Column(name = "state", unique = false, table = "registration_data")
    private String state;

    @Column(name = "zip", unique = false, table = "registration_data")
    private int zip;

    @Column(name = "homephone", unique = true, table = "registration_data")
    private String homePhone;

    @Column(name = "cellphone", unique = true, table = "registration_data")
    private String cellPhone;

    @Column(name = "email", unique = true, table = "registration_data")
    private String email;

    public RegistrationEntity(RegistrationRequest employeeRequestDTO) {
        this.username = employeeRequestDTO.getUsername();
        this.firstName = employeeRequestDTO.getFirstName();
        this.lastName = employeeRequestDTO.getLastName();
        this.streetAddress = employeeRequestDTO.getStreetAddress();
        this.city = employeeRequestDTO.getCity();
        this.state = employeeRequestDTO.getState();
        this.zip = employeeRequestDTO.getZip();
        this.cellPhone = employeeRequestDTO.getCellPhone();
        this.homePhone = employeeRequestDTO.getHomePhone();
        this.email = employeeRequestDTO.getEmail();
    }

}

