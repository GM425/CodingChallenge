package com.service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.dtos.RegistrationRequest;
import com.service.dtos.RegistrationResponse;
import com.service.kafka.KafkaProducer;
import com.service.models.RegistrationEntity;
import com.service.repository.RegistrationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private KafkaProducer kafkaProducer;
    public RegistrationService(RegistrationRepository registrationRepository, KafkaProducer kafkaProducer){
        this.registrationRepository = registrationRepository;
        this.kafkaProducer = kafkaProducer;
    }

        public RegistrationResponse createEmployee(RegistrationRequest registrationRequest) {
            //check email to confirm email is not present, if it is throw error
        RegistrationEntity employee = new RegistrationEntity(registrationRequest);

        registrationRepository.save(employee);

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(employee);
        } catch(JsonProcessingException exception){
            throw new RuntimeException("Error serializing");
        }

        kafkaProducer.send("employees", payload);

        return new RegistrationResponse(employee);
  
    }

    public List<RegistrationResponse> getAllEmployees() {
        List<RegistrationEntity> employees = registrationRepository.findAll();
        return employees.stream().map(RegistrationResponse::new).collect(Collectors.toList());
    }


    public Optional<RegistrationResponse> getEmployeeById(long id) {
        return registrationRepository.findById(id).map(RegistrationResponse::new);
    }

        // EmployeeEntity employee = mapToEmployeeResponse(employeeRepository.findById(id));


    public String deleteEmployee(long id) {
        registrationRepository.deleteById(id);
        return "the employee with the id of " + id + " has been deleted";
    }

    public RegistrationResponse updateCurrentEmployee(RegistrationRequest updatedRegistration, long id) {
        
        RegistrationEntity registration = registrationRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee with the id of " + id + " does not exist."));

        String username = updatedRegistration.getUsername();
        if (username != null && !registration.getUsername().equals(username)) {
            registration.setUsername(username);
        } 

        String firstName = updatedRegistration.getFirstName();
        if (firstName != null && !registration.getFirstName().equals(firstName)) {
            registration.setFirstName(firstName);
        }

        String lastName = updatedRegistration.getLastName();
        if (lastName != null && !registration.getLastName().equals(lastName)) {
            registration.setLastName(lastName);
        } 

        String streetAddress = updatedRegistration.getStreetAddress();
        if (streetAddress != null && !registration.getLastName().equals(streetAddress)) {
            registration.setStreetAddress(streetAddress);
        } 

        String city = updatedRegistration.getCity();
        String newString = "change";
        if (registration.getCity() != null){
            newString = registration.getCity();
        }
        if (city != null && !newString.equals(city)) {
            registration.setCity(city);
        }

        String state = updatedRegistration.getState();
        if (state != null && !registration.getState().equals(state)) {
            registration.setState(state);
        } 

        int zip = updatedRegistration.getZip();
        if (registration.getZip() != zip) {
            registration.setZip(zip);
        }

        String cellPhone = updatedRegistration.getCellPhone();
        if (cellPhone != null && !registration.getCellPhone().equals(cellPhone)) {
            registration.setCellPhone(cellPhone);
        } 

        String homePhone = updatedRegistration.getHomePhone();
        if (homePhone != null && !registration.getHomePhone().equals(homePhone)) {
            registration.setHomePhone(homePhone);
        }

        String email = updatedRegistration.getEmail();
        Optional<RegistrationEntity> emailOptional = registrationRepository.findByEmail(email);

        if (emailOptional.isPresent() && !email.equals(registration.getEmail())) {
            throw new IllegalStateException("This email is already taken");
        } else {
            registration.setEmail(email);
        }
    
        registrationRepository.save(registration);
        
        return new RegistrationResponse(registration);

    }
}
