package com.example.jymapplication.service;

import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeService {
    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    TrainingTypeRepository trainingTypeRepository;

    public List<TrainingType> getAll() {
        return trainingTypeRepository.findAll();
    }
}
