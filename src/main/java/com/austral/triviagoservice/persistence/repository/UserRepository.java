package com.austral.triviagoservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.austral.triviagoservice.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
