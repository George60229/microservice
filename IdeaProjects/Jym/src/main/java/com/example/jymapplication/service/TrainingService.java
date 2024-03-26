package com.example.jymapplication.service;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainingRepository;
import com.example.jymapplication.request.TrainingDTO;
import com.example.jymapplication.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainingService {
    TrainingRepository trainingRepository;
    @Autowired
    Converter converter;
    @Autowired
    TrainerService trainerService;
    @Autowired
    TraineeService traineeService;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training createTraining(TrainingDTO trainingDTO) {
        Training training = converter.trainingRequestToModel(trainingDTO);
        training.setTrainer(trainerService.getTrainer(trainingDTO.getTrainerUsername()));
        training.setTrainee(traineeService.getByUsername(trainingDTO.getTraineeUsername()));
        log.info("Create training: " + training);
        return trainingRepository.save(training);
    }

}
