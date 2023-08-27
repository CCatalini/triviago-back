package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.InvitationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UUIDRepository extends JpaRepository<InvitationCode, String> {
}
