package com.example.jymapplication.service;

import com.example.jymapplication.dto.ActionType;
import com.example.jymapplication.dto.TrainerRequest;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainingRepository;
import com.example.jymapplication.request.TrainingDTO;
import com.example.jymapplication.request.TrainingTrainerDTO;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        if (trainingDTO.getActionType().equals(ActionType.DELETE)) {
            trainingRepository.deleteByTrainingNameAndTrainingDateAndTrainingDuration(trainingDTO.getTrainingName(),
                    trainingDTO.getTrainingDate(), trainingDTO.getTrainingDuration());
        }
        Training training = converter.trainingRequestToModel(trainingDTO);
        training.setTrainer(trainerService.getTrainer(trainingDTO.getTrainerUsername()));
        training.setTrainee(traineeService.getByUsername(trainingDTO.getTraineeUsername()));
        log.info("Create training: " + training);
        return trainingRepository.save(training);
    }

    public TrainerRequest getTrainerRequest(TrainingDTO trainingDTO) {
        String username = trainingDTO.getTrainerUsername();
        Trainer trainer = trainerService.getTrainer(username);
        TrainerRequest trainerRequest = new TrainerRequest();
        trainerRequest.setTrainerFirstName(trainer.getFirstName());
        trainerRequest.setTrainerLastName(trainer.getLastName());
        trainerRequest.setActive(trainer.getIsActive());
        trainerRequest.setUsername(trainer.getUsername());
        trainerRequest.setActionType(trainingDTO.getActionType());
        Set<TrainingResponse> trainings = trainerService.getTraining(new TrainingTrainerDTO(trainer.getUsername(),
                null, null, null));
        int duration = trainingDTO.getTrainingDuration();
        for (TrainingResponse t : trainings) {
            duration += t.getTrainingDuration();
        }
        trainerRequest.setTrainingDuration(duration);
        trainerRequest.setTrainingDate(trainingDTO.getTrainingDate());
        return trainerRequest;
    }
}
