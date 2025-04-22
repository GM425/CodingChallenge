package com.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.models.RegistrationEntity;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long>{
    List<RegistrationEntity> findAll();
    Optional<RegistrationEntity> findByUsername(String username);
    Optional<RegistrationEntity> findByEmail(String email);

}
