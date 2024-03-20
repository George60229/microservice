package com.example.jymapplication.service;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainingService {
    TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training createTraining(Training training) {
        log.info("Create training: " + training.toString());
        return trainingRepository.save(training);
    }

    public Training selectTraining(int trainingId) {
        log.info("Select training with id: " + trainingId);
        if (trainingRepository.findById(trainingId).isPresent()) {
            return trainingRepository.findById(trainingId).get();
        }
        return null;
    }
}
