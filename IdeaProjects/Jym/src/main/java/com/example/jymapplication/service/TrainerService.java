package com.example.jymapplication.service;

import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class TrainerService {
    TrainerDao trainerDao;
    @Autowired
    Converter converter;

    @Autowired
    public TrainerService(TrainerDao trainerDAO) {
        this.trainerDao = trainerDAO;
    }

    public Trainer createTrainer(TrainerDto trainerDto) {
        log.info("Create trainer:" + trainerDto.toString());
        return trainerDao.add(converter.trainerDtoToModel(trainerDto));
    }

    public Trainer updateTrainer(TrainerDto trainerDto, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Update trainer:" + trainerDto.toString());
            return trainerDao.update(converter.trainerDtoToModel(trainerDto));
        }
        return null;
    }

    public Trainer selectTrainer(int trainerId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Select trainer with id:" + trainerId);
            return trainerDao.get(trainerId);
        }
        return null;
    }

    private boolean checkCredential(AuthorizeDto authorizeDto) {
        return trainerDao.checkPassword(authorizeDto);
    }

    public Trainer changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change password:" + username);
            return trainerDao.changePassword(username, newPassword);
        }
        return null;
    }

    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            trainerDao.changeActivity(username);
        }
    }

    public Set<Training> getTrainingByCriteria(String username, TrainerCriteria criteria, Object value, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            return trainerDao.getTrainingByCriteria(username, criteria, value);
        }
        return null;
    }
}
