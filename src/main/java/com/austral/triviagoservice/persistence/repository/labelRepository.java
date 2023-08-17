package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface labelRepository extends JpaRepository<Label, Long> {

}
