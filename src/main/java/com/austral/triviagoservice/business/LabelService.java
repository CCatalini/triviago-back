package com.austral.triviagoservice.business;

import com.austral.triviagoservice.presentation.dto.LabelDto;

import java.util.List;

public interface LabelService {
    List<LabelDto> findAll();
}
