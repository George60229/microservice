package com.example.jymapplication.service;


import com.example.jymapplication.comand.CommandExecutor;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.enums.TraineeCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TraineeRepository;
import com.example.jymapplication.utils.Converter;
import com.example.jymapplication.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean checkCredential(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    public Trainee createTrainee(TraineeDto traineeDto) {
        log.info("Create trainee:" + traineeDto.toString());
        Trainee trainee = converter.traineeDtoToModel(traineeDto);
        trainee.setPassword(userUtils.generatePassword());
        trainee.setUsername(userUtils.generateUsername(trainee, (Set<Trainee>) traineeRepository.findAll()));
        return traineeRepository.save(converter.traineeDtoToModel(traineeDto));
    }

    @Transactional
    public Trainee editTrainee(TraineeDto traineeDto, AuthorizeDto authorizeDto, int userId) {

        if (checkCredential(authorizeDto)) {
            if (traineeRepository.findById(userId).isPresent()) {
                Trainee trainee = traineeRepository.findById(userId).get();
                trainee.setFirstName(traineeDto.getFirstName());
                trainee.setLastName(traineeDto.getLastName());
                trainee.setAddress(traineeDto.getAddress());
                trainee.setDateOfBirth(traineeDto.getDateOfBirth());
                traineeRepository.save(trainee);
            }

        }
        return null;
    }


    public void deleteTrainee(int traineeId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Delete trainee with id:" + traineeId);
            traineeRepository.deleteById(traineeId);
        }

    }

    public Trainee selectTrainee(int traineeId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            if (traineeRepository.findById(traineeId).isPresent()) {
                traineeRepository.findById(traineeId).get();
            }
        }
        return null;
    }


    public Set<Trainer> getFreeTrainers(String username, AuthorizeDto authorizeDto) {

        if (checkCredential(authorizeDto)) {
            Trainee trainee = getByUsername(username);
            Set<Training> trainings = trainee.getTrainings();
            Set<Trainer> trainers = trainerService.getAll();
            for (Training training : trainings) {
                trainers.remove(training.getTrainer());
            }
            return trainers;
        }
        return null;
    }

    public Trainee changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            Trainee trainee = traineeRepository.findByUsername(username);
            trainee.setPassword(newPassword);
            traineeRepository.save(trainee);
        }
        return null;
    }


    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            traineeRepository.updateIsActiveBy(!traineeRepository.findByUsername(username).getIsActive());
        }
    }


    public Set<Training> getTrainingByCriteria(String username, TraineeCriteria criteria, Object value,
                                               AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            Trainee trainee = getByUsername(username);
            CommandExecutor commandExecutor = new CommandExecutor(trainee.getTrainings(), value);

            return commandExecutor.executeCommand(criteria.name());
        }
        return null;
    }


    public void delete(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            traineeRepository.deleteByUsername(username);
        }
    }

    private Trainee getByUsername(String username) {
        return traineeRepository.findByUsername(username);
    }


}

