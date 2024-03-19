package com.example.jymapplication.service;

import com.example.jymapplication.dao.TraineeDao;
import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.enums.TraineeCriteria;
import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TraineeService {
    TraineeDao traineeDao;
    TrainerDao trainerDao;


    @Autowired
    Converter converter;


    @Autowired
    public TraineeService(TraineeDao traineeDao, TrainerDao trainerDao) {
        this.traineeDao = traineeDao;
        this.trainerDao = trainerDao;
    }

    public boolean checkCredential(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    public Trainee createTrainee(TraineeDto traineeDto) {
        log.info("Create trainee:" + traineeDto.toString());
        return traineeDao.add(converter.traineeDtoToModel(traineeDto));
    }

    public Trainee editTrainee(TraineeDto traineeDto, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? traineeDao.update(converter.traineeDtoToModel(traineeDto)) : null;
    }


    public void deleteTrainee(int traineeId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Delete trainee with id:" + traineeId);
            traineeDao.delete(traineeId);
        }

    }

    public Trainee selectTrainee(int traineeId, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? traineeDao.get(traineeId) : null;
    }


    public Set<Trainer> getFreeTrainers(String username, AuthorizeDto authorizeDto) {

        if (checkCredential(authorizeDto)) {
            Trainee trainee = getByUsername(username);
            Set<Training> trainings = trainee.getTrainings();
            Set<Trainer> trainers = trainerDao.findAll();
            for (Training training : trainings) {
                trainers.remove(training.getTrainer());
            }
            return trainers;
        }
        return null;
    }

    public Trainee changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? traineeDao.changePassword(username, newPassword) : null;
    }


    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            traineeDao.changeActivity(username);
        }
    }


    public Set<Training> getTrainingByCriteria(String username, TraineeCriteria criteria, Object value, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? traineeDao.getTrainingByCriteria(username, criteria, value) : null;
    }


    public void delete(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            traineeDao.deleteByUsername(username);
        }
    }

    private Trainee getByUsername(String username) {
        return traineeDao.getByUsername(username);
    }

    public Set<Trainee> getAll() {
        return traineeDao.getAll();
    }


}
