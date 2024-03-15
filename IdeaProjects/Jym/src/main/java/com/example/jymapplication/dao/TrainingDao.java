package com.example.jymapplication.dao;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TrainingDao {

    TrainingRepository trainingRepository;

    public TrainingDao(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training add(Training training) {
        return trainingRepository.save(training);
    }

    public Training get(int id) {
        Optional<Training> optionalTrainee = trainingRepository.findById(id);
        return optionalTrainee.orElse(null);
    }
}
