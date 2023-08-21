package com.austral.triviagoservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.austral.triviagoservice.persistance.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
