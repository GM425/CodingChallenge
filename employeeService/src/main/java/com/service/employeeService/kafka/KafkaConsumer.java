package com.service.employeeService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.employeeService.model.EmployeeEntity;
import com.service.employeeService.repository.EmployeeRepository;

@Service
public class KafkaConsumer {

    private final EmployeeRepository employeeRepository;

    public KafkaConsumer(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @KafkaListener( topics="employees", groupId = "employee-group")
    public void listen(String payload){

        ObjectMapper objectMapper = new ObjectMapper();

        EmployeeEntity employee = null;

        try {
            employee = objectMapper.readValue(payload, EmployeeEntity.class);
        } catch (JsonProcessingException exception){
            exception.printStackTrace();
        }

        if (employee != null) {
            employeeRepository.save(employee);
        }

    }
}
