package com.example.jymapplication.service;

import com.example.jymapplication.dao.TraineeDao;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.enums.TraineeCriteria;
import com.example.jymapplication.enums.TrainerCriteria;
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

    @Autowired
    Converter converter;


    @Autowired
    public TraineeService(TraineeDao trainerDAO) {
        this.traineeDao = trainerDAO;
    }

    public boolean checkCredential(AuthorizeDto authorizeDto) {
        return traineeDao.checkPassword(authorizeDto);
    }

    public Trainee createTrainee(TraineeDto traineeDto) {
        log.info("Create trainee:" + traineeDto.toString());
        return traineeDao.add(converter.traineeDtoToModel(traineeDto));
    }

    public Trainee editTrainee(TraineeDto traineeDto, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Update trainee:" + traineeDto.toString());
            return traineeDao.update(converter.traineeDtoToModel(traineeDto));
        }
        return null;
    }

    public void deleteTrainee(int traineeId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Delete trainee with id:" + traineeId);
            traineeDao.delete(traineeId);
        }

    }

    public Trainee selectTrainee(int traineeId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Select trainee with id:" + traineeId);
            return traineeDao.get(traineeId);
        }
        return null;
    }

    public Set<Trainer> getFreeTrainers(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            return traineeDao.getFreeTrainers(username);
        }
        return null;
    }

    public Trainee changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change password:" + username);
            return traineeDao.changePassword(username, newPassword);
        }
        return null;
    }

    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            traineeDao.changeActivity(username);
        }

    }

    public Set<Training> getTrainingByCriteria(String username, TraineeCriteria criteria, Object value, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            return traineeDao.getTrainingByCriteria(username, criteria, value);
        }
        return null;
    }

    public void delete(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            traineeDao.deleteByUsername(username);
        }
    }

}
