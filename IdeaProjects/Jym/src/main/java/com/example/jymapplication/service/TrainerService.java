package com.example.jymapplication.service;

import com.example.jymapplication.comand.CommandExecutor;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainerRepository;
import com.example.jymapplication.request.TrainerUpdateDTO;
import com.example.jymapplication.request.TrainingTrainerDTO;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.utils.Converter;
import com.example.jymapplication.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    UserUtils userUtils;
    @Autowired
    Converter converter;

    public TrainerResponse createTrainer(TrainerDto trainerDto) {
        log.info("Create trainer:" + trainerDto.toString());
        Trainer trainer = converter.trainerDtoToModel(trainerDto);
        trainer.setPassword(userUtils.generatePassword());
        trainer.setUsername(userUtils.generateUsername(trainer, trainerRepository.findAll()));
        return converter.trainerModelToResponse(trainerRepository.save(trainer));
    }

    @Transactional
    public TrainerProfile updateTrainer(TrainerUpdateDTO trainerUpdateDTO) {
        Trainer trainer = trainerRepository.findByUsername(trainerUpdateDTO.getUsername());
        trainer.setFirstName(trainerUpdateDTO.getFirstName());
        trainer.setLastName(trainerUpdateDTO.getLastName());
        trainer.setSpecialization(trainerUpdateDTO.getSpecialization());
        trainer.setIsActive(trainerUpdateDTO.getIsActive());
        return converter.getTrainerProfile(trainerRepository.save(trainer), getMyTrainees(trainer));
    }

    public TrainerProfile selectTrainer(String username) {
        Trainer trainer = trainerRepository.findByUsername(username);
        return converter.getTrainerProfile(trainer, getMyTrainees(trainer));
    }

    public Trainer getTrainer(String username) {
        return trainerRepository.findByUsername(username);
    }

    public void changeActivity(String username, boolean isActive) {
        log.info("Change activity status:" + username);
        Trainer trainer = trainerRepository.findByUsername(username);
        trainer.setIsActive(isActive);
        trainerRepository.save(trainer);
    }

    public Set<Training> getTrainingByCriteria(String username, TrainerCriteria criteria, Object value) {
        log.info("Change activity status:" + username);
        Trainer trainee = getByUsername(username);
        CommandExecutor commandExecutor = new CommandExecutor(trainee.getTrainings(), value);
        return commandExecutor.executeCommand(criteria.name());
    }

    private Trainer getByUsername(String username) {
        return trainerRepository.findByUsername(username);
    }

    public Set<Trainer> getAll() {
        return (Set<Trainer>) trainerRepository.findAll();
    }

    public Set<Trainee> getMyTrainees(Trainer trainer) {
        Set<Training> trainings = trainer.getTrainings();
        Set<Trainee> trainees = new HashSet<>();
        for (Training training : trainings) {
            trainees.add(training.getTrainee());
        }
        return trainees;
    }

    public Set<TrainingResponse> getTraining(TrainingTrainerDTO trainerRequest) {
        Set<Training> trainings = new HashSet<>();
        if (trainerRequest.getPeriodFrom() != null) {
            trainings.addAll(getTrainingByCriteria(trainerRequest.getUsername(),
                    TrainerCriteria.fromDate, trainerRequest.getPeriodFrom()));
        }
        if (trainerRequest.getPeriodTo() != null) {
            trainings.addAll(getTrainingByCriteria(trainerRequest.getUsername(),
                    TrainerCriteria.toDate, trainerRequest.getPeriodTo()));
        }
        if (trainerRequest.getTraineeName() != null) {
            trainings.addAll(getTrainingByCriteria(trainerRequest.getUsername(),
                    TrainerCriteria.traineeName, trainerRequest.getTraineeName()));
        }
        Set<TrainingResponse> trainingResponses = new HashSet<>();
        for (Training training : trainings) {
            trainingResponses.add(converter.trainingModelToResponse(training));
        }
        return trainingResponses;
    }
}


