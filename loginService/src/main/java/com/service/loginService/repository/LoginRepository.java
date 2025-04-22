package com.service.loginService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.loginService.models.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
    List<LoginEntity> findAll();
    Optional<LoginEntity> findByUsername(String username);
}
