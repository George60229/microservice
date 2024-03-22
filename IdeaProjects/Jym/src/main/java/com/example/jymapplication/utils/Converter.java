package com.example.jymapplication.utils;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.request.TrainingRequest;
import com.example.jymapplication.response.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Converter {
    public Trainee traineeDtoToModel(TraineeDto dto) {

        return new Trainee(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getAddress());
    }

    public Trainer trainerDtoToModel(TrainerDto dto) {
        return new Trainer(dto.getFirstName(), dto.getLastName(), dto.getSpecialization());
    }

    public TraineeResponse traineeModelToResponse(Trainee trainee) {
        return new TraineeResponse(trainee.getUsername(), trainee.getPassword());
    }

    public TrainerResponse trainerModelToResponse(Trainer trainer) {
        return new TrainerResponse(trainer.getFirstName(), trainer.getLastName(), trainer.getSpecialization());
    }

    public TraineeInfo getTraineeInfo(Trainee trainee) {
        return new TraineeInfo(trainee.getFirstName(), trainee.getLastName(), trainee.getUsername());
    }

    public TraineeProfile getTraineeProfile(Trainee trainee, Set<Trainer> trainers) {

        HashSet<TrainerInfo> newTrainers = new HashSet<>();
        for (Trainer trainer : trainers) {
            newTrainers.add(getTrainerInfo(trainer));
        }

        return new TraineeProfile(trainee.getFirstName(), trainee.getLastName(), trainee.getDateOfBirth(), trainee.getAddress(), trainee.getIsActive(), newTrainers);
    }

    public TrainerProfile getTrainerProfile(Trainer trainer, Set<Trainee> trainees) {

        HashSet<TraineeInfo> newTrainees = new HashSet<>();
        for (Trainee trainee : trainees) {
            newTrainees.add(getTraineeInfo(trainee));
        }

        return new TrainerProfile(trainer.getFirstName(), trainer.getLastName(), trainer.getUsername(), trainer.getSpecialization(), trainer.getIsActive(), newTrainees);
    }

    public TrainerInfo getTrainerInfo(Trainer trainer) {
        return new TrainerInfo(trainer.getFirstName(), trainer.getLastName(), trainer.getSpecialization(), trainer.getIsActive());
    }

    public TrainingResponse trainingModelToResponse(Training training) {
        return new TrainingResponse(training.getTrainingName(), training.getTrainingDate(),
                training.getTrainingType(), training.getTrainingDuration(),
                training.getTrainer().getUsername());
    }

    public Training trainingRequestToModel(TrainingRequest trainingRequest) {
        Training training = new Training();
        training.setTrainingName(trainingRequest.getTrainingName());
        training.setTrainingDate(trainingRequest.getTrainingDate());
        training.setTrainingDuration(trainingRequest.getTrainingDuration());
        return training;
    }
}
