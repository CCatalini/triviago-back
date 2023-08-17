package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.LabelService;
import com.austral.triviagoservice.persistence.domain.Label;
import com.austral.triviagoservice.persistence.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class labelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }
}
