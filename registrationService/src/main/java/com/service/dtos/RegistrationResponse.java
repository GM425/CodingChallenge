package com.service.dtos;

import com.service.models.RegistrationEntity;

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
public class RegistrationResponse {
    
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

    public RegistrationResponse(RegistrationEntity registrationEntity) {
        this.id = registrationEntity.getId();
        this.username = registrationEntity.getUsername();
        this.firstName = registrationEntity.getFirstName();
        this.lastName = registrationEntity.getLastName();
        this.streetAddress = registrationEntity.getStreetAddress();
        this.city = registrationEntity.getCity();
        this.state = registrationEntity.getState();
        this.zip = registrationEntity.getZip();
        this.cellPhone = registrationEntity.getCellPhone();
        this.homePhone = registrationEntity.getHomePhone();
        this.email = registrationEntity.getEmail();
    }
}
