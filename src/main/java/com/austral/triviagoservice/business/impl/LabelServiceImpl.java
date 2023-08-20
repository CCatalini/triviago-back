package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.LabelService;
import com.austral.triviagoservice.persistence.domain.Label;
import com.austral.triviagoservice.persistence.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }
}
