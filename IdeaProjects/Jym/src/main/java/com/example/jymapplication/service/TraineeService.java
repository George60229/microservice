package com.example.jymapplication.service;


import com.example.jymapplication.comand.CommandExecutor;
import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.enums.TraineeCriteria;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TraineeRepository;
import com.example.jymapplication.request.TraineeRequest;
import com.example.jymapplication.request.TrainingTraineeRequest;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.response.TrainerInfo;
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
public class TraineeService {
    TraineeRepository traineeRepository;
    TrainerService trainerService;


    @Autowired
    Converter converter;

    @Autowired
    UserUtils userUtils;


    @Autowired
    public TraineeService(TraineeRepository traineeRepository, TrainerService trainerService) {
        this.traineeRepository = traineeRepository;
        this.trainerService = trainerService;
    }



    public TraineeResponse createTrainee(TraineeDto traineeDto) {

        log.info("Create trainee:" + traineeDto.toString());

        Trainee trainee = converter.traineeDtoToModel(traineeDto);
        trainee.setPassword(userUtils.generatePassword());
        trainee.setUsername(userUtils.generateUsername(trainee,traineeRepository.findAll()));
        return converter.traineeModelToResponse(traineeRepository.save(trainee));
    }

    @Transactional
    public TraineeProfile editTrainee(TraineeRequest traineeRequest) {


        Trainee trainee = traineeRepository.findByUsername(traineeRequest.getUsername());
        trainee.setFirstName(traineeRequest.getFirstName());
        trainee.setLastName(traineeRequest.getLastName());
        trainee.setAddress(traineeRequest.getAddress());
        trainee.setDateOfBirth(traineeRequest.getDateOfBirth());
        trainee.setIsActive(traineeRequest.getIsActive());
        return converter.getTraineeProfile(traineeRepository.save(trainee), getMyTrainers(trainee));


    }


    public Set<TrainerInfo> updateTrainers(String username, Set<String> trainers) {
        Trainee trainee = traineeRepository.findByUsername(username);
        Set<Trainer> myTrainers = new HashSet<>();
        Set<TrainerInfo> trainerInfos = new HashSet<>();
        for (String name : trainers) {
            if (name == null) {
                return null;
            }
            myTrainers.add(trainerService.getTrainer(name));
            trainerInfos.add(converter.getTrainerInfo(trainerService.getTrainer(name)));
        }
        trainee.setTrainers(myTrainers);
        traineeRepository.save(trainee);
        return trainerInfos;

    }


    public Set<TrainerInfo> getFreeTrainers(String username) {


        Trainee trainee = getByUsername(username);
        Set<Training> trainings = trainee.getTrainings();
        Set<Trainer> trainers = trainerService.getAll();
        for (Training training : trainings) {
            trainers.remove(training.getTrainer());
        }
        Set<TrainerInfo> trainerInfos = new HashSet<>();
        for (Trainer trainer : trainers) {
            trainerInfos.add(converter.getTrainerInfo(trainer));
        }
        return trainerInfos;

    }

    public Set<Trainer> getMyTrainers(Trainee trainee) {


        Set<Training> trainings = trainee.getTrainings();
        Set<Trainer> trainers = new HashSet<>();
        for (Training training : trainings) {
            trainers.add(training.getTrainer());
        }
        return trainers;


    }


    public void changeActivity(String username, boolean isActive) {

        log.info("Change activity status:" + username);
        traineeRepository.updateIsActiveBy(isActive);

    }


    public Set<Training> getTrainingByCriteria(String username, TraineeCriteria criteria, Object value) {


        Trainee trainee = getByUsername(username);
        CommandExecutor commandExecutor = new CommandExecutor(trainee.getTrainings(), value);

        return commandExecutor.executeCommand(criteria.name());

    }


    public void delete(int id) {
        traineeRepository.deleteById(id);
    }

    public Trainee getByUsername(String username) {
        return traineeRepository.findByUsername(username);
    }

    public TraineeProfile get(int id) {
        if (traineeRepository.findById(id).isPresent()) {
            Trainee trainee = traineeRepository.findById(id).get();

            Set<Trainer> trainers = getMyTrainers(trainee);
            return converter.getTraineeProfile(traineeRepository.findById(id).get(), trainers);
        }
        return null;
    }

    public Set<TrainingResponse> getTraining(TrainingTraineeRequest trainingTraineeRequest) {
        Set<Training> trainings = new HashSet<>();

        if (trainingTraineeRequest.getTrainingType() != null) {
            trainings.addAll(getTrainingByCriteria(trainingTraineeRequest.getUsername(),
                    TraineeCriteria.trainingType, trainingTraineeRequest.getTrainingType()));
        }
        if (trainingTraineeRequest.getPeriodFrom() != null) {
            trainings.addAll(getTrainingByCriteria(trainingTraineeRequest.getUsername(),
                    TraineeCriteria.fromDate, trainingTraineeRequest.getPeriodFrom()));
        }
        if (trainingTraineeRequest.getPeriodTo() != null) {
            trainings.addAll(getTrainingByCriteria(trainingTraineeRequest.getUsername(),
                    TraineeCriteria.toDate, trainingTraineeRequest.getPeriodTo()));
        }

        if (trainingTraineeRequest.getTrainerName() != null) {
            trainings.addAll(getTrainingByCriteria(trainingTraineeRequest.getUsername(),
                    TraineeCriteria.trainerName, trainingTraineeRequest.getTrainerName()));
        }

        Set<TrainingResponse> trainingResponses = new HashSet<>();
        for (Training training : trainings) {
            trainingResponses.add(converter.trainingModelToResponse(training));
        }
        return trainingResponses;

    }


}

