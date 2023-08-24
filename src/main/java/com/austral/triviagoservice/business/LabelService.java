package com.austral.triviagoservice.business;

import com.austral.triviagoservice.persistence.domain.Label;

import java.util.List;

public interface LabelService {
    List<Label> findAll();
}
