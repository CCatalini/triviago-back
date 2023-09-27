package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.LabelService;
import com.austral.triviagoservice.persistence.repository.LabelRepository;
import com.austral.triviagoservice.presentation.dto.LabelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public List<LabelDto> findAll() {
        return labelRepository.findAll().stream().map(LabelDto::new).collect(Collectors.toList());
    }
}
